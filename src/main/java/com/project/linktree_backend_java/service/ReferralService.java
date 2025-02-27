package com.project.linktree_backend_java.service;

import com.project.linktree_backend_java.model.Referral;
import com.project.linktree_backend_java.repository.ReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReferralService {
    @Autowired
    private ReferralRepository referralRepository;

    public List<Referral> getReferralsByUser(String username) {
        return referralRepository.findByReferredBy(username);
    }

    public Referral saveReferral(Referral referral) {
        return referralRepository.save(referral);
    }
}
