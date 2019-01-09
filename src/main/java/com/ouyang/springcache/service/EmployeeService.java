package com.ouyang.springcache.service;

import com.ouyang.springcache.dao.EmployeeMapper;
import com.ouyang.springcache.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    public List<Employee> list(){
        List<Employee> list = employeeMapper.list();
        return list;
    }
}
