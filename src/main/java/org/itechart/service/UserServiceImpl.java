package org.itechart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itechart.entity.jpa.user.User;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Page<User> findAll(int page, int pageSize) {
        return userRepository.findAll(new PageableSortedById(page, pageSize));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void save(String userJson, MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);

        if (user.getId() != null) {
            throw new IllegalArgumentException("Failed to save user with not null id");
        }

        userRepository.save(user);

        saveFile(file, user);
    }

    @Override
    public void update(String userJson, MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);

        if (user.getId() == null) {
            throw new IllegalArgumentException("Failed to update user with id == null");
        }

        userRepository.save(user);

        saveFile(file, user);
    }

    private void saveFile(MultipartFile file, User user) throws IOException {
        if (!file.isEmpty()) {
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, new File("d:\\IdeaProjects\\angular-adventure\\src\\main\\webapp\\img\\user" + user.getId() + ".jpg").toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
