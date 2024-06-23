package com.lixega.blog.repository;

import com.lixega.blog.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String name);
    Optional<UserAccount> findByEmail(String email);
}
