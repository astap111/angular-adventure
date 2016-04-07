package org.itechart.service;

import org.itechart.entity.User;

import java.util.List;

public interface UserService {
    void update(User user);

    void save(User user);

    User findOne(Long id);

    List<User> findAll();

    User findByLogin(String login);
}
