package com.challenge.endpoints;


import java.util.List;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRest {

    private UserService service;

    @Autowired
    public UserRest(UserService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User user = service.findById(id).orElse(null);
        return user;
    }

    @GetMapping(params = "accelerationName")
    public List<User> findByAccelerationName(@RequestParam("accelerationName") String name) {
        List<User> byAccelerationName = service.findByAccelerationName(name);
        return byAccelerationName;
    }

    @GetMapping(params = "companyId")
    public List<User> getByCompanyId(@RequestParam("companyId") Long id) {
        return service.findByCompanyId(id);
    }

}