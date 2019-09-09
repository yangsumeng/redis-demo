package com.yangsm.demo.redis.lock;

/**
 * @description:
 *
 * @author: yangsm
 *
 * @create: 2019-09-09 18:18
 **/
public class RedisLock {
    /**
     *@descption
     *@param key 锁标识             判断是否锁定
     *@param value 客户端标识       解锁验证
     *@param timeOut 过期时间       防止死锁
     *@return
     *@author yangsm
     *@date  2019/9/9
     */
    public Boolean lock(String key,String value,Long timeOut){
        return true;
    }
}
