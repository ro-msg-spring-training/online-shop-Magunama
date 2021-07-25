package ro.msg.learning.shop.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class NoAuthSecurityMode {
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // Permit all requests without authentication
                .authorizeRequests().anyRequest().permitAll();
        http.headers().frameOptions().disable();
    }
}
