package org.itechart.service;

import org.itechart.entity.User;
import org.itechart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("Failed to update user with id == null");
        }
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Failed to save user with not null id");
        }
        userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Page<User> findAll(int page, int pageSize) {
        return userRepository.findAll(new Pageable() {
            @Override
            public int getPageNumber() {
                return page;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public int getOffset() {
                return page * pageSize;
            }

            @Override
            public Sort getSort() {
                return null;
            }
        });
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
