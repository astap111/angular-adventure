package org.itechart.service;

import org.itechart.entity.jpa.user.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User findOne(Long id);

    Page<User> findAll(int page, int pageSize);

    User findByLogin(String login);

    void save(String userJson, MultipartFile file) throws IOException;

    void update(String userJson, MultipartFile file) throws IOException;
}
