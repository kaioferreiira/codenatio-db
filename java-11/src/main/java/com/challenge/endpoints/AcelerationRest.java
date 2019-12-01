package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acceleration")
public class AcelerationRest {


    private AccelerationService accelerationService;

    @Autowired
    public AcelerationRest(AccelerationService accelerationService) {
        this.accelerationService = accelerationService;
    }

    @GetMapping("/{id}")
    public Acceleration findById(@PathVariable(value = "id") Long id) {
        Optional<Acceleration> byId = accelerationService.findById(id);
        return byId.get();
    }

    @GetMapping(params = "companyId")
    public List<Acceleration> findByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        List<Acceleration> byCompanyId = accelerationService.findByCompanyId(companyId);
        return byCompanyId;
    }


}
