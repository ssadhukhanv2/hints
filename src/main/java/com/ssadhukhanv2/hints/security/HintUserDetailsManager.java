package com.ssadhukhanv2.hints.security;

import com.ssadhukhanv2.hints.model.User;
import com.ssadhukhanv2.hints.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HintUserDetailsManager implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    HintUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User details not found for the user : " + userName);
        }
        return new HintUserDetails(user);

    }
}
