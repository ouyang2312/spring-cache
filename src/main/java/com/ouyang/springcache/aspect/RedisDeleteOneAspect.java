package com.ouyang.springcache.aspect;

import com.ouyang.springcache.annotation.RedisDeleteOne;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author oy
 * @description
 * @date 2019/4/17
 */
@Aspect
@Component
public class RedisDeleteOneAspect {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(com.ouyang.springcache.annotation.RedisDeleteOne)")
    public void annotationPointCut(){

    }

     @Before("annotationPointCut()")
     public void before(JoinPoint joinPoint){
         MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
         Method method = sign.getMethod();

         Object[] args = joinPoint.getArgs();
         StringBuffer key = new StringBuffer();
         for (int i = 0; i < args.length; i++) {
             key.append(args[i]);
         }
         RedisDeleteOne annotation = method.getAnnotation(RedisDeleteOne.class);
         String id = annotation.value();
         //拿到id 直接使用redis去删除对应的记录
         Boolean delete = stringRedisTemplate.delete(id+"::"+key.toString());
         System.out.println(delete);
     }

}
