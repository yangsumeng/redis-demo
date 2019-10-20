package com;

import com.yangsm.demo.redis.RedisApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class RedisApplicationTest {
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = RedisApplication.class)
    public class MainTest {
//        @Autowired
//        private AService aService;

        @Before
        public void before() {
            System.out.println("start=============");
        }

        @Test
        public void testStartUp() {
//            aService.doSmthing();
            System.out.println("--------------测试----------");
        }
    }
}
