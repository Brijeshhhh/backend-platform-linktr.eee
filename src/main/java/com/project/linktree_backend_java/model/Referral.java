package com.project.linktree_backend_java.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;

    @ManyToOne
    @JoinColumn(name = "referred_user_id", nullable = false)
    private User referredUser;

    @Column(nullable = false)
    private LocalDateTime dateReferred = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "PENDING"; // Can be PENDING, SUCCESSFUL
}
