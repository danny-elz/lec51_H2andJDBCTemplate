package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class securityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("danny.elzein@sheridancollege.ca")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
        UserDetails guest = User.withUsername("guest@guest.com")
                .password(passwordEncoder.encode("password"))
                .roles("GUEST")
                .build();
        return new InMemoryUserDetailsManager(user, guest);
    }

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/secure/**").hasRole("USER")
                        .requestMatchers("/", "/js/**", "/css/**", "/images/**", "/permission-denied").permitAll()
                        .requestMatchers("/**").denyAll()
                )
                .formLogin(form -> form.loginPage("/login").permitAll())
                .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
                .logout(logout -> logout.permitAll())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
        return encoder;
    }
}
