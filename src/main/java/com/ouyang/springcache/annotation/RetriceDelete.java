package com.ouyang.springcache.annotation;

import com.ouyang.springcache.entity.Employee;

import java.lang.annotation.*;

/**
 * 删除注解
 * @author oy
 * @date 2019/5/5
 * @param
 * @return
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetriceDelete {
    String value();

    Class clazz();
}
