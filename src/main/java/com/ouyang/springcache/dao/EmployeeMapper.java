package com.ouyang.springcache.dao;

import com.ouyang.springcache.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper 这里可以不写 在启动类上写
public interface EmployeeMapper {

    public List<Employee> list();

}
