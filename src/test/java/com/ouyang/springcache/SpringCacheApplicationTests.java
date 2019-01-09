package com.ouyang.springcache;

import com.google.gson.Gson;
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
        System.out.println(redisTemplate.boundListOps("list1").leftPop());
        Gson gson = new Gson();
        Object list1 = redisTemplate.boundListOps("list1").leftPop();

    }

}

