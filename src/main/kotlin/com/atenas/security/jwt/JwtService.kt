package com.atenas.security.jwt

import com.atenas.domain.entity.Guy
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class JwtService {
    @Value("\${security.jwt.expiration}")
    lateinit var expiration: String

    @Value("\${security.jwt.signature-key}")
    lateinit var signatureKey: String

    fun generateToken(guy: Guy): String {
        val exp: Long = expiration.toLong()
        val dateTimeExpiration: LocalDateTime = LocalDateTime.now().plusMinutes(exp)
        val instant: Instant = dateTimeExpiration.atZone(ZoneId.systemDefault()).toInstant()
        val date: Date = Date.from(instant)

        return Jwts.builder()
                .setSubject(guy.login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact()
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
                    .setSigningKey(signatureKey)
                    .parseClaimsJws(token)
                    .body
    }

    fun validToken(token: String): Boolean {
        return try {
            val claims: Claims = getClaims(token)
            val expiration: Date = claims.expiration
            val dateExpiration: LocalDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
            LocalDateTime.now().isBefore(dateExpiration)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getUserLogin(token: String): String {
        return getClaims(token).subject as String
    }

}
