package com.challenge.endpoints;

import java.util.List;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge")
public class ChallengeRest {


    private ChallengeService challengeService;

    @Autowired
    public ChallengeRest(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public List<Challenge> findByAccelerationIdAndUserId(@RequestParam Long accelerationId, @RequestParam Long userId) {
        List<Challenge> byAccelerationIdAndUserId = challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
        return byAccelerationIdAndUserId;
    }


}
