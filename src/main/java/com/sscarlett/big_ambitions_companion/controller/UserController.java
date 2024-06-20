package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Users;
import com.sscarlett.big_ambitions_companion.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/new")
    public Integer postNewUser(@RequestBody Users users) {
        return userService.postNewUser(users);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public Integer loginUser(@RequestBody Users users) {
        System.out.println("[LOGIN]: " + users);
        return userService.loginUser(users);
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public Users getUserInfo(@PathVariable Integer userId) {
        return userService.getUserInfo(userId);
    }

    @PatchMapping(value = "/update")
    public void patchUser(@RequestBody Users users) {
        userService.patchUser(users);
    }

    @PatchMapping(value = "/reset-password")
    public void resetPassword(@RequestBody Users users) {
        userService.resetPassword(users);
    }
}
