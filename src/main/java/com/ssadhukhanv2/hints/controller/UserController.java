package com.ssadhukhanv2.hints.controller;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.model.User;
import com.ssadhukhanv2.hints.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST
    // node/
    @PostMapping("/")
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // GET
    // node/{nodeId}
    @GetMapping("/{userIdentifier}/nodes")
    public @ResponseBody
    List<Node> getRootNodes(@PathVariable(name = "userIdentifier") Long id) {
        return userService.getRootNodesForUser(id);
    }


    // GET
    // node/{nodeId}
    @GetMapping("/{userIdentifier}")
    public @ResponseBody
    User getUser(@PathVariable(name = "userIdentifier") Long id) {
        return userService.getUser(id);
    }


    // PUT
    // node/{nodeId}
    @PutMapping("/{userIdentifier}")
    public @ResponseBody
    User createOrUpdateUser(@PathVariable(name = "userIdentifier") Long id, @RequestBody User user) {
        user.setUserId(id);
        return userService.updateUser(user);
    }

    // PATCH
    // node/{nodeId}
    //https://www.mscharhag.com/api-design/rest-partial-updates-patch
    @PatchMapping("/{userIdentifier}")
    public @ResponseBody
    User updateUser(@PathVariable(name = "userIdentifier") Long id, @RequestBody User user) {
        user.setUserId(id);
        return userService.updateUser(user);
    }

}
