package org.itechart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itechart.entity.User;
import org.itechart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Secured(value = "ROLE_ADMIN")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
