package ro.msg.learning.shop.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ro.msg.learning.shop.security.BasicSecurityMode;
import ro.msg.learning.shop.security.FormSecurityMode;
import ro.msg.learning.shop.security.NoAuthSecurityMode;
import ro.msg.learning.shop.security.SecurityCustomFilter;


@Getter
@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.mode}")
    private SecurityMode securityMode;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("qwerty1234"))
                .roles("USER");
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityMode.equals(SecurityMode.NONE)) {
            new NoAuthSecurityMode().configure(http);
            return;
        }

        if (securityMode.equals(SecurityMode.BASIC)) {
            new BasicSecurityMode().configure(http);
        } else {
            new FormSecurityMode().configure(http);
        }

        http.addFilterAfter(new SecurityCustomFilter(),
                BasicAuthenticationFilter.class);
    }

    public enum SecurityMode {BASIC, FORM, NONE}
}