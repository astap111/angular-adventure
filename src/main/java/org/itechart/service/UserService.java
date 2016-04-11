package org.itechart.service;

import org.itechart.entity.user.User;
import org.springframework.data.domain.Page;

public interface UserService {
    void update(User user);

    void save(User user);

    User findOne(Long id);

    Page<User> findAll(int page, int pageSize);

    User findByLogin(String login);
}
