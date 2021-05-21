package com.hotechcourse.ouath.configuration

import com.hotechcourse.ouath.auth.RestOAuth2AccessDeniedHandler
import com.hotechcourse.ouath.auth.RestOAuth2AuthenticationEntryPoint
import com.hotechcourse.ouath.auth.RestOAuth2AuthenticationFilter
import com.hotechcourse.ouath.auth.RestOAuth2AuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val restfulOAuth2AuthorizationFilter: RestOAuth2AuthorizationFilter,
    private val restfulOAuth2AuthenticationFilter: RestOAuth2AuthenticationFilter
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()

            .addFilterBefore(restfulOAuth2AuthorizationFilter, BasicAuthenticationFilter::class.java)
            .addFilterBefore(restfulOAuth2AuthenticationFilter, BasicAuthenticationFilter::class.java)

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
//            .oauth2Login()
//            .loginPage("/login.html")
//            .and()
            .exceptionHandling()
//            .authenticationEntryPoint(RestOAuth2AuthenticationEntryPoint())
            .accessDeniedHandler(RestOAuth2AccessDeniedHandler())

            .and()
            .authorizeRequests()
            .antMatchers("/login.html").permitAll()
            .antMatchers("/v3/api-docs/**", "/antMatchersswagger-ui/**", "/swagger-ui.html").permitAll()
//            .antMatchers("/v3/api-docs/**", "/swagger-ui/**swagger-ui", "/swagger-ui.html").hasRole("USER")
            .anyRequest().authenticated()
    }
}
