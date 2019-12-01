package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyRest {

    private CompanyService companyService;

    @Autowired
    public CompanyRest(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/{id}")
    public Company findById(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        return company.get();
    }

    @GetMapping(params = "accelerationId")
    public List<Company> findByAccelerationId(@RequestParam("accelerationId") Long accelerationId) {
        List<Company> byAccelerationId = companyService.findByAccelerationId(accelerationId);
        return byAccelerationId;
    }

    @GetMapping(params = "userId")
    public List<Company> findByUserId(@RequestParam("userId") Long userId) {
        List<Company> byUserId = companyService.findByUserId(userId);
        return byUserId;
    }


}
