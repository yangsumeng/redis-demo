package com.yangsm.demo.redis.lock;

import com.yangsm.demo.redis.bean.ApplicationContextBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @description: Redis实现分布式锁
 * @author: yangsm
 * @create: 2019-09-09 18:18
 **/
public class RedisLock {

    private static RedisTemplate getRedisTemplate(){
        return (RedisTemplate)ApplicationContextBean.applicationContext.getBean("redisCacheTemplate");
    }
    /**
     *@descption
     * 加锁操作：jedis.set(key,value,"NX","EX",timeOut)【保证加锁的原子操作】
     * key就是redis的key值作为锁的标识，value在这里作为客户端的标识，只有key-value都比配才有删除锁的权利【保证安全性】
     * 通过timeOut设置过期时间保证不会出现死锁【避免死锁】
     * NX，EX什么意思？
     * NX：只有这个key不存才的时候才会进行操作，if not exists；
     * EX：设置key的过期时间为秒，具体时间由第5个参数决定
     *  spring boot 2.0之后 setIfAbsent 代替 setNX
     *@param key 锁标识             判断是否锁定
     *@param value 客户端标识       解锁验证
     *@param timeOut 过期时间       防止死锁
     *@return
     *@author yangsm
     *@date  2019/9/9
     */
    public static Boolean lock(String key,String value,Long timeOut){
        //SET key value NX PX 3000
        Boolean result = getRedisTemplate().opsForValue().setIfAbsent(key,value, timeOut,TimeUnit.SECONDS);
        return result;
    }


    /**
     * 释放分布式锁
     * @param key 锁
     * @param value 请求标识,身份证明
     * @return 是否释放成功
     */
    public static boolean unLock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        System.out.println("执行脚本：" + script);
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Object.class);
        Object i = getRedisTemplate().execute(redisScript, Collections.singletonList(key), value);
        if(i != null ){
            return true;
        }
        return false;
    }
}
