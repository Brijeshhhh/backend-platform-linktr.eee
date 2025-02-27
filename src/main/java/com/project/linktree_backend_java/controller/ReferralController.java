package com.project.linktree_backend_java.controller;

import com.project.linktree_backend_java.model.Referral;
import com.project.linktree_backend_java.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referrals")
public class ReferralController {
    @Autowired
    private ReferralService referralService;

    @GetMapping("/{username}")
    public List<Referral> getUserReferrals(@PathVariable String username) {
        return referralService.getReferralsByUser(username);
    }

    @PostMapping("/save")
    public Referral saveReferral(@RequestBody Referral referral) {
        return referralService.saveReferral(referral);
    }
}
