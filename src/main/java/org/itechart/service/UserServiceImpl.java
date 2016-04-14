package org.itechart.service;

import org.itechart.entity.user.User;
import org.itechart.other.PageableSortedById;
import org.itechart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final String PATH_TO_PHOTOS = "d:\\IdeaProjects\\angular-adventure\\src\\main\\webapp\\photo\\";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void update(User user) throws IOException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("Failed to update user with id == null");
        }

        byte[] bytes = Base64Utils.decodeFromString(user.getPhoto());
        FileOutputStream out = new FileOutputStream(PATH_TO_PHOTOS + user.getId() + ".jpg");
        out.write(bytes);
        out.close();

        userRepository.save(user);
    }

    @Override
    public void save(User user) throws IOException {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Failed to save user with not null id");
        }

        userRepository.save(user);

        byte[] bytes = Base64Utils.decodeFromString(user.getPhoto());
        FileOutputStream out = new FileOutputStream(PATH_TO_PHOTOS + user.getId() + ".jpg");
        out.write(bytes);
        out.close();
    }

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
}
