package com.ouyang.springcache.aspect;

import com.ouyang.springcache.annotation.RedisDeleteOne;
import com.ouyang.springcache.annotation.RetriceDelete;
import com.ouyang.springcache.entity.Employee;
import com.ouyang.springcache.entity.Retrice;
import com.ouyang.springcache.service.EmployeeService;
import com.ouyang.springcache.service.RetriceService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除的时候 加入到回收站中
 * @author oy
 * @date 2019/5/5
 * @param
 * @return
 */
@Aspect
@Component
public class RetriceDeleteAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RetriceService retriceService;

    @Pointcut("@annotation(com.ouyang.springcache.annotation.RetriceDelete)")
    public void annotationPointCut(){

    }

     @Before("annotationPointCut()")
     public void before(JoinPoint joinPoint) throws Exception{
         System.out.println("之前删除注解进来了");
         MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
         Method method = sign.getMethod();

         Object[] args = joinPoint.getArgs();

         RetriceDelete annotation = method.getAnnotation(RetriceDelete.class);
         String value = annotation.value();
         Class clazz = annotation.clazz();
         //获取当前的名称
         String name = clazz.getName();
         String[] split = name.split("\\.");
         String s = split[split.length - 1];
         char[] chars = new char[1];
         chars[0] = s.charAt(0);
         String temp = new String(chars);
         Object employeeService = applicationContext.getBean(s.replaceFirst(temp, temp.toLowerCase()));

         Map<Object,Object> map = new HashMap<>();//装配结果
         //对象
         EmployeeService employeeService1 = null;
         Employee employee = null;
         Class employeeClass = null;

         //employee模块
         if(employeeService instanceof EmployeeService){
             employeeService1 = (EmployeeService)employeeService;
             employee = employeeService1.get(args[0].toString());
             employeeClass = employee.getClass();

             if(employeeService1 != null){
                 Field[] declaredFields = employeeClass.getDeclaredFields();
                 for (Field field : declaredFields) {
                     if(!field.getName().equals("serialVersionUID")){
                         map.put(field.getName(),getResult(field.getName(),employee));
                     }
                 }
             }
         }

         Retrice retrice = new Retrice();
         retrice.setName((String)map.get(value));
         retrice.setDeleteId((int)map.get("id"));
//         retrice.setName(name);
//         retrice.setDeleteId(id);
         retriceService.add(retrice);
     }

    private static Object getResult(Object fieldName, Object vo) {
        try {
            Class<?> aClass = vo.getClass();
            Field declaredField = aClass.getDeclaredField(fieldName.toString());
            declaredField.setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), aClass);
            Method readMethod = pd.getReadMethod();

            return readMethod.invoke(vo);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("之后删除注解进来了");
//        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
//        Method method = sign.getMethod();
//
//        Object[] args = joinPoint.getArgs();
//        StringBuffer key = new StringBuffer();
//        for (int i = 0; i < args.length; i++) {
//            key.append(args[i]);
//        }
//        RetriceDelete annotation = method.getAnnotation(RetriceDelete.class);
//        String id = annotation.value();
//        retriceService.update(id,1);
    }
}
