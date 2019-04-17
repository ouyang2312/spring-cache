package com.ouyang.springcache.service;

import com.ouyang.springcache.annotation.RedisDeleteOne;
import com.ouyang.springcache.dao.EmployeeMapper;
import com.ouyang.springcache.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames="emp"/*,cacheManager = "employeeCacheManager"*/) //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable
    public List<Employee> list(){
        List<Employee> list = employeeMapper.list();
        return list;
    }

    @Cacheable("id")//缓存存储的key
    public Employee get(String id) {
        Employee employee = employeeMapper.getEmployee(id);
        return employee;
    }

    @CachePut
    public boolean update(Employee employee) {
        boolean b = employeeMapper.updateEmployee(employee);
        return b;
    }

//    @CacheEvict
    @RedisDeleteOne(value = "id")
    public boolean delete(String id) {
//        boolean b = employeeMapper.deleteEmployee(id);
////        return b;
        return true;
    }

    @CachePut
    public boolean insert(Employee employee) {
        boolean b = employeeMapper.insertEmployee(employee);
        return b;
    }

}
