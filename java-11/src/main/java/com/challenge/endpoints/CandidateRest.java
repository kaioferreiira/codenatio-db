package com.challenge.endpoints;


import java.util.List;
import java.util.Optional;

import com.challenge.entity.Candidate;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateRest {


    private CandidateService candidateService;

    @Autowired
    public CandidateRest(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public Candidate getCandidateById(@PathVariable("userId") Long userId, @PathVariable("accelerationId") Long accelerationId, @PathVariable("companyId") Long companyId) {
        Optional<Candidate> byId = candidateService.findById(userId, companyId, accelerationId);
        return byId.get();
    }

    @GetMapping(params = "companyId")
    public List<Candidate> findByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        List<Candidate> byCompanyId = candidateService.findByCompanyId(companyId);
        return byCompanyId;
    }

    @GetMapping(params = "accelerationId")
    public List<Candidate> findByAccelerationId(@RequestParam(value = "accelerationId") Long accelerationId) {
        List<Candidate> byAccelerationId = candidateService.findByAccelerationId(accelerationId);
        return byAccelerationId;
    }





}
