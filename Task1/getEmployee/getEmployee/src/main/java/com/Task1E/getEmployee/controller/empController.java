package com.Task1E.getEmployee.controller;

import com.Task1E.getEmployee.model.emp;
import com.Task1E.getEmployee.repo.emp_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class empController {
    @Autowired
    emp_repo er;
    @GetMapping("/getemployee")
    public List<emp> getemp()
    {
        return (List<emp>) er.findAll();
    }
}
