package com.ouyang.springcache.dao;

import com.ouyang.springcache.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper 这里可以不写 在启动类上写
public interface EmployeeMapper {

    List<Employee> list();

    boolean updateEmployee(Employee employee);

    Employee getEmployee(@Param("id") String id);

    boolean deleteEmployee(@Param("id") String id);

    boolean insertEmployee(Employee employee);
}
