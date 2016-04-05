package org.itechart.controller;

import org.itechart.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class TestController {

    @PostConstruct
    private void postC() {
        System.out.println("CONSTRUCTED!");
    }

    @RequestMapping(value = "/test")
    public User getUser() {
        return new User(1, "Alala");
    }
}
