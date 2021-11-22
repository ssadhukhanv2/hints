package com.ssadhukhanv2.hints.service;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.model.User;
import com.ssadhukhanv2.hints.repo.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService<User, Long> {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<Node> getRootNodesForUser(Long userId) {
        return userRepository.findRootNodes(userId);
    }
}
