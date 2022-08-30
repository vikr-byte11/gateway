package com.Task1E.postEmployee.controller;

import com.Task1E.postEmployee.model.emp;
import com.Task1E.postEmployee.repo.emp_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class empController {

    @Autowired
    emp_repo er;


    @PostMapping("/saveemployee")
    public String savee(@RequestParam String name)
    {
        emp e=new emp();
        e.setName(name);
        er.save(e);
        return "Your Data is saved :)";
    }


}
