package org.itechart.service;

import org.itechart.entity.Role;
import org.itechart.entity.User;
import org.itechart.repository.RoleRepository;
import org.itechart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        Role role = null;
        if (user.getRole() != null && user.getRole().getRoleName() != null) {
            role = roleRepository.findByRoleName(user.getRole().getRoleName());
        }
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllExceptSuperAdmin();
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
