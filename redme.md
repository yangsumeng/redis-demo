# Redis 测试类

1. redis通用接口,删除，新增，读取
    1. string 类型
    2. list 类型
    3. 类型0-String，1-Hash，2-List，3-Set，4-ZSet
    
2.  spring boot cache
    1.CacheController
    
3. 分布式锁
    1. 就性能而言，redis很明显优于zookeeper
    2. 健壮性而言，zookeeper很明显优于redis
    
4. 单机Redis
    3. 互斥性
    4. 安全性
        1. 加锁的人才能解锁
        2. 误解锁
       ~~~
       可能出现的情况就是a去查询发现持有锁，就在准备解锁。
       这时候忽然a持有的锁过期了，同时b去获得锁，因为a锁过期，b拿到锁，
       这时候a继续执行第二步进行解锁如果不加校验，就将b持有的锁就给删除了
       ~~~
    5. 避免死锁
      ~~~
        设置timeOut
        设置锁之后，设置过期时间，两步操作非原子
      ~~~
    6. 保证加锁与解锁操作是原子性操作
      ~~~
        设置超时时间也要原子性
      ~~~
   
5. 集群Redis
        
