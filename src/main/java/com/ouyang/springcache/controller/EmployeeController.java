package com.ouyang.springcache.controller;

import com.ouyang.springcache.entity.Employee;
import com.ouyang.springcache.entity.Retrice;
import com.ouyang.springcache.service.EmployeeService;
import com.ouyang.springcache.service.RetriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RetriceService retriceService;

    @Autowired
    private ApplicationContext applicationContext;

    @DeleteMapping(value = "/testDelete/{id}")
    public List<Retrice> testDelete(@PathVariable("id") String  id){
        employeeService.delete(id);
        //查询retrice表中的数据
        return retriceService.list();
    }

    @GetMapping(value = "/test")
    public Employee test(){
        EmployeeService employeeService = (EmployeeService)applicationContext.getBean("employeeService");
        Employee employee = employeeService.get("1");
        return employee;
    }

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
    public boolean delete(@PathVariable("id") String id){
        boolean b = employeeService.delete(id);
        return b;
    }

}
