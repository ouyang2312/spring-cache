package com.ouyang.springcache.controller;

import com.ouyang.springcache.entity.Employee;
import com.ouyang.springcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employee/list")
    public List<Employee> list(){
        return employeeService.list();
    }

    @GetMapping(value="/employee/{id}")
    public Employee get(@PathVariable("id") String id){
        Employee employee =  employeeService.get(id);
        return employee;
    }

    @PutMapping(value="/employee/update")
    public boolean update(@RequestBody Employee employee){
        boolean b = employeeService.update(employee);
        return b;
    }

    @PostMapping(value="/employee/insert")
    public boolean insert(@RequestBody Employee employee){
        boolean b = employeeService.insert(employee);
        return b;
    }

    @DeleteMapping(value="/employee/{id}")
    public boolean update(@PathVariable("id") String id){
        boolean b = employeeService.delete(id);
        return b;
    }

}
