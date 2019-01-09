package com.ouyang.springcache.dao;

import com.ouyang.springcache.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    public List<Employee> list();

}
