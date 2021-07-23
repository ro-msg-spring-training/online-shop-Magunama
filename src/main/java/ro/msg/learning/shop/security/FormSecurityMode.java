package ro.msg.learning.shop.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class FormSecurityMode {
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/h2-console/**").hasRole("USER")
                .anyRequest().authenticated().and().formLogin();
    }
}
