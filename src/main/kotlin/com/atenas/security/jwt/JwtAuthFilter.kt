package com.atenas.security.jwt

import com.atenas.exceptions.InvalidTokenException
import com.atenas.service.impl.GuyServiceImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthFilter (private val jwtService: JwtService,
                     private val guyService: GuyServiceImpl) : OncePerRequestFilter() {


    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse, filterChain: FilterChain) {

        val authorization: String = request.getHeader("authorization")

        if (authorization.startsWith("Bearer")) {
            val token: String = authorization.split(" ")[1]
            val isValid: Boolean = jwtService.validToken(token)

            if (isValid) {
                val userLogin: String = jwtService.getUserLogin(token)
                val guy: UserDetails = guyService.loadUserByUsername(userLogin)
                val user = UsernamePasswordAuthenticationToken(
                        guy, null, guy.authorities)
                user.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = user
            }
            else {
                throw InvalidTokenException()
            }

        }

        filterChain.doFilter(request, response)
    }

}
