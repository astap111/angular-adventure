package org.itechart.repository;

import org.itechart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Query("select u from User u")
    List<User> findAllExceptSuperAdmin();
}
