package com.omar.library.library_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {
    // if we want to store usernames and password use -> LDAP or database
    // here we will use In Memory

    @Bean
    public InMemoryUserDetailsManager createInMemoryUserDetailsManager() {
        UserDetails user1 = createUserDetails("omar","123");
        UserDetails user2 = createUserDetails("joo","123");
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails createUserDetails(String username, String password) {
        return User.builder()
                .passwordEncoder(input -> passwordEncoder().encode(input))
                .username(username)
                .password(password)
                .roles("USER","ADMIN")
                .build();

    }

}
