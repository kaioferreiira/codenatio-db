package com.challenge.endpoints;

import java.util.List;

import com.challenge.entity.Submission;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submission")
public class SubmissionRest {

    private SubmissionService submissionService;

    @Autowired
    public SubmissionRest(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping()
    public List<Submission> findByChallengeIdAndAccelerationId(
            @RequestParam Long challengeId,
            @RequestParam Long accelerationId) {

        List<Submission> byChallengeIdAndAccelerationId = submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);

        return byChallengeIdAndAccelerationId;
    }





}


