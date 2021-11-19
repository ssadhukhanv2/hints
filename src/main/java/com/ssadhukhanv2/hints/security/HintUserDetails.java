package com.ssadhukhanv2.hints.security;

import com.ssadhukhanv2.hints.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HintUserDetails implements UserDetails {
    private final User user;

    public HintUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        // Later we can implement logic for Custom Authority & Role
        // https://stackoverflow.com/questions/41770156/spring-add-custom-user-details-to-spring-security-user/41772510
        /*grantedAuthorityList.add(new SimpleGrantedAuthority("ADMIN"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("MANAGER"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("INTERN"));*/


        //Here we have used java 9 optional interfaces & Streams
        //For more details reference:
        // https://stackify.com/optional-java/
        // https://stackify.com/streams-guide-java-8/
        List<GrantedAuthority> grantedAuthorityList = user.getAuthorityList().stream()
                .map(authority
                        -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
