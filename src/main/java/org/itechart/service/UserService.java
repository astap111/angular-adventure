package org.itechart.service;

import org.itechart.entity.user.User;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    void update(User user) throws IOException;

    void save(User user) throws IOException;

    User findOne(Long id);

    Page<User> findAll(int page, int pageSize);

    User findByLogin(String login);
}
