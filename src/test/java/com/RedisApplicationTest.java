package com;

import com.yangsm.demo.redis.RedisApplication;
import com.yangsm.demo.redis.lock.RedisLock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTest {

//        @Autowired
//        private AService aService;

        @Before
        public void before() {
            System.out.println("start=============");
        }

        @Test
        public void testStartUp() {
            boolean isOk = RedisLock.lock("key","value001",1000L);
            System.out.println("结果："+isOk);
//            aService.doSmthing();
            RedisLock.unLock("name","yangsumeng");
            System.out.println("--------------测试----------");
        }
}
