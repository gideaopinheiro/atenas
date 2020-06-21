package com.atenas.config

import com.atenas.security.jwt.JwtAuthFilter
import com.atenas.security.jwt.JwtService
import com.atenas.service.impl.GuyServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var guyService: GuyServiceImpl

    @Bean
    fun jwtFilter(): OncePerRequestFilter {
        return JwtAuthFilter(this.jwtService, this.guyService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/clients/**")
                .hasRole("USER")
                .antMatchers("/api/v1/profile-picture/**")
                .hasRole("USER")
                .antMatchers("/api/v1/credit-card/**")
                .hasRole("USER")
                .antMatchers("/api/v1/users/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

}
