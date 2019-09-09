package com.yangsm.demo.redis.dao;


import com.yangsm.demo.redis.vo.RedisParams;

public interface RedisDao {
    void save(RedisParams params, int expirTime);
    
    void save(RedisParams params);
    
    Object read(RedisParams params);
    
    void delete(RedisParams params);
}
