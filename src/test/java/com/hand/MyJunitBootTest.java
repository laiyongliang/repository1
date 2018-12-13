package com.hand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.dao.UserMapper;
import com.hand.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBootApplication.class)
public class MyJunitBootTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        List<User> all = userMapper.findAll();
        System.out.println(all);

    }
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Test
    public void testRedis(){
        // 先从redis中进行获取
        String users = redisTemplate.boundValueOps("users").get();
        if(users == null){
            //如果redis中不存在，从数据库中获取,并存入redis
            List<User> all = userMapper.findAll();
            //先用jackson将集合转换为json字符串
            ObjectMapper objectMapper =new ObjectMapper();

            try {
                users = objectMapper.writeValueAsString(all);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            redisTemplate.boundValueOps("users").set(users);
            System.out.println("======从数据库中获取user======");
        }else{
            System.out.println("=======从redis中获取");
        }
        System.out.println(users);
    }

}
