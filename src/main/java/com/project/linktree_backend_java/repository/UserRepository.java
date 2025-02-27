package com.project.linktree_backend_java.repository;

import com.project.linktree_backend_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
    Optional<User> findByReferralCode(String referralCode);

    List<User> findByReferredBy(String referralCode);
}
