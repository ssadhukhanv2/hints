package com.ssadhukhanv2.hints.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


// Summary of Security Implemented

// Spring:

// AuthenticationManager uses AuthenticationProvider, which internally uses UserDetailsService & PasswordEncoder to authenticate users.
// UserDetailsService makes uses of UserDetails
// Spring's implementation of AuthenticationManager -> ProviderManager

// Our Approach in this class:

// Configuration Class:
// HintSecurityConfig extends WebSecurityConfigurerAdapter(Spring) and provides bean for PasswordEncoder

// AuthenticationProvider: (THIS IS IMPLEMETED IN THIS CLASS)
// HintAuthenticationProvider provides  concrete implementation for AuthenticationProvider
// which internally uses UserDetailsService & PasswordEncoder to authenticate users.

// UserDetailsService:
// HintUserDetailsManager provides  concrete implementation for UserDetailsService.

// PasswordEncoder:
// SCryptPasswordEncoder provides the concrete implementation for PasswordEncoder.
// HintSecurityConfig provides the Bean class for this.


@Component
public class HintAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String userPassword = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (userDetails != null) {
            if (passwordEncoder.matches(userPassword, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userName, userPassword, userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        } else {
            throw new BadCredentialsException("No user registered with this user!!");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
