package com.ouyang.springcache;

import com.google.gson.Gson;
import com.ouyang.springcache.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCacheApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试数据源
     */
    @Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("119.23.187.31",6379);
        jedis.auth("123456");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void testRedisStringTemplate(){
        Long add = stringRedisTemplate.boundSetOps("a").add("aa");
        System.out.println(add);
    }

    @Test
    public void testRedisTemplate(){
        String[] string = new String[]{"a","b","c"};
        Gson gson = new Gson();
        String s = gson.toJson(string);
        redisTemplate.boundListOps("list1").leftPush(s);
    }

    @Test
    public void testRedisTemplate2(){
        Employee employee = new Employee(5,"ouy","aas@qq.com",1,2);
        redisTemplate.boundValueOps(employee.getLastName()).set(employee);
    }

    /**
     *
     */
    @Test
    public void testRedisTemplate3(){
        Object ouy = redisTemplate.boundValueOps("ouy").get();
        System.out.println(ouy);
    }

    /**
     * Redis常见的五大数据类型
     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue()[String（字符串）]
     *  stringRedisTemplate.opsForList()[List（列表）]
     *  stringRedisTemplate.opsForSet()[Set（集合）]
     *  stringRedisTemplate.opsForHash()[Hash（散列）]
     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    public void test01(){
        //给redis中保存数据
        //stringRedisTemplate.opsForValue().append("msg","hello");
//		String msg = stringRedisTemplate.opsForValue().get("msg");
//		System.out.println(msg);

//		stringRedisTemplate.opsForList().leftPush("mylist","1");
//		stringRedisTemplate.opsForList().leftPush("mylist","2");
    }


}

