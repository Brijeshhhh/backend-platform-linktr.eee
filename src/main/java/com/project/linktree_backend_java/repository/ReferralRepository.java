package com.project.linktree_backend_java.repository;

import com.project.linktree_backend_java.model.Referral;
import com.project.linktree_backend_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferrer(User referrer);

    List<Referral> findByReferredBy(String username);
}
