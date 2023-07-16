package pl.kozlowski.moviedb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val SWAGGER_WHITELIST = arrayOf(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
        )

        http {
            csrf { disable() }
            authorizeHttpRequests {
                SWAGGER_WHITELIST.forEach {
                    authorize(it, permitAll)
                }
                authorize("/login", permitAll)
                authorize("/shows", permitAll)
                authorize("/movies", permitAll)
                authorize(anyRequest, authenticated)
            }
            httpBasic { }
        }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val owner = User.withDefaultPasswordEncoder()
            .username("owner")
            .password("password")
            .roles("OWNER")
            .build()
        return InMemoryUserDetailsManager(owner)
    }
}
