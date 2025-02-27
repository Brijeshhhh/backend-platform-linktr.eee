package com.project.linktree_backend_java.service;

import com.project.linktree_backend_java.model.User;
import com.project.linktree_backend_java.repository.UserRepository;
import com.project.linktree_backend_java.repository.ReferralRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ReferralRepository referralRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, ReferralRepository referralRepository) {
        this.userRepository = userRepository;
        this.referralRepository = referralRepository;
    }

    public User registerUser(String username, String email, String password, String referralCode) {
        Optional<User> referrer = userRepository.findByReferralCode(referralCode);
        User user = new User(username,email,passwordEncoder.encode(password));
        user.setReferralCode("REF" + username + System.currentTimeMillis());
        referrer.ifPresent(user::setReferredBy);
        return userRepository.save(user);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public List<User> getUserReferrals(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.findByReferredBy(user.getReferralCode());
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
