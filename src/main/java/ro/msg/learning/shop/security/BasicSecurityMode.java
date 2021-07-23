package ro.msg.learning.shop.security;

import lombok.Setter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Setter
public class BasicSecurityMode {

    private BasicAuthEntryPoint authEntryPoint;

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/h2-console/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(this.authEntryPoint);
    }
}
