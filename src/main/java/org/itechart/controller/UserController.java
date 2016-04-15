package org.itechart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itechart.configuration.security.SecurityUser;
import org.itechart.entity.user.User;
import org.itechart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
    public void createUser(@RequestBody User user, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void handleFormUpload(@RequestPart(value = "user") String userString, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userString, User.class);
        userService.save(user);
        if (!file.isEmpty()) {
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, new File("d:\\IdeaProjects\\angular-adventure\\src\\main\\webapp\\img\\img1.jpg").toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                LOGGER.error("File wasn't saved correctly", e);
            }
        }
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
