package com.ssadhukhanv2.hints.service;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService<T extends User, S extends Number> {
    public T createUser(T user);

    public T updateUser(T user);

    public void deleteUser(S userId);

    public T getUser(S userId);

    public List<Node> getRootNodesForUser(S userId);

}
