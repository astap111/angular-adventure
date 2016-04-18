package org.itechart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itechart.configuration.security.SecurityUser;
import org.itechart.entity.jpa.user.User;
import org.itechart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> getUsers(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return userService.findAll(page, pageSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createUser(@RequestPart(value = "user") String userJson, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        userService.save(userJson, file);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateUser(@RequestPart(value = "user") String userJson, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        userService.update(userJson, file);

    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public SecurityUser getCurrentUser() {
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(null);
        return user;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
