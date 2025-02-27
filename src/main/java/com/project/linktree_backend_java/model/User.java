package com.project.linktree_backend_java.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String referralCode;

    @ManyToOne
    @JoinColumn(name = "referred_by")
    private User referredBy;

    @OneToMany(mappedBy = "referredBy", cascade = CascadeType.ALL)
    private Set<User> referrals = new HashSet<>();

    public User(String testUser, String mail, String password123) {
    }

    public User() {

    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
    }
}
