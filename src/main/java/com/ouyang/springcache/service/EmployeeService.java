package com.ouyang.springcache.service;

import com.ouyang.springcache.dao.EmployeeMapper;
import com.ouyang.springcache.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames="emp"/*,cacheManager = "employeeCacheManager"*/) //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Cacheable
    public List<Employee> list(){
        List<Employee> list = employeeMapper.list();
        return list;
    }

    @Cacheable
    public Employee get(String id) {
        Employee employee = employeeMapper.getEmployee(id);
        return employee;
    }

    @CachePut
    public boolean update(Employee employee) {
        boolean b = employeeMapper.updateEmployee(employee);
        return b;
    }

    @CacheEvict
    public boolean delete(String id) {
        boolean b = employeeMapper.deleteEmployee(id);
        return b;
    }

    @CachePut
    public boolean insert(Employee employee) {
        boolean b = employeeMapper.insertEmployee(employee);
        return b;
    }
}
