package com.ssadhukhanv2.hints.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Slf4j
@Configuration
public class HintSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * To Enable H2 console access with Spring Security:
         * Include the URL of h2 console in the antMatchers & permit it.
         * Disable the csrf check:
         * This should be done because h2 is a separate ecosystem with separate security build in.
         * So the jsessionid for the h2 console is possibly not known to the security being configured outside the app.
         * How-ever this may lead to CROSS SITE REQUEST FORGERY (CSRF) attack: https://www.synopsys.com/glossary/what-is-csrf.html
         * Allow frameOptions from same Origin:
         * h2 makes use of frame options but by default frame option is disabled in spring security
         * so we enable frame-option from same source.
         * Frame option may lead to CLICK JACKING ATTACK  https://en.wikipedia.org/wiki/Clickjacking#/media/File:Clickjacking.png
         * https://stackoverflow.com/questions/28647136/how-to-disable-x-frame-options-response-header-in-spring-security
         * https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
         * https://stackoverflow.com/questions/24948651/spring-security-difference-between-and-url-pattern-in-spring-security
         * */

        log.info("Security is being configured");
        http.authorizeRequests((requests) -> {
            //((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) requests.anyRequest()).authenticated();
            requests.antMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
        });
        // Disable the csrf check
        http.csrf().disable();
        // Allow frameOptions from same Origin
        http.headers().frameOptions().sameOrigin();
        http.formLogin();
        http.httpBasic();
    }


    //    /*
//    *
//    * Configuring users with in-memory authentication
//    *
//    * */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("inmemory_user1")
//                .password("inmemory_password1")
//                .authorities("admin")
//                .and()
//                .withUser("inmemory_user2")
//                .password("inmemory_password2")
//                .authorities("read")
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    @Bean//("encoder")
    public PasswordEncoder passwordEncoder() {
        System.out.println("Setting Password Encoder!!!!!");
        // return NoOpPasswordEncoder.getInstance();
        return new SCryptPasswordEncoder();
        //return new BCryptPasswordEncoder();
    }

}
