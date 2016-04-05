package org.itechart.controller;

import org.itechart.domain.SimpleDomain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DomainController {

    @RequestMapping("/domain")
    public SimpleDomain getDomain(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new SimpleDomain(1, "Hello, " + name);
    }
}
