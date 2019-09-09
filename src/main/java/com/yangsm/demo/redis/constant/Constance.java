package com.yangsm.demo.redis.constant;

/**
 * Created by hrzong on 2018/5/3.
 */
public interface Constance {

    int REDIS_DEFAULT_EXPIRE_TIME = 21600;

    int REDIS_TYPE_STRING = 0;
    int REDIS_TYPE_HASH = 1;
    int REDIS_TYPE_LIST = 2;
    int REDIS_TYPE_SET = 3;
    int REDIS_TYPE_ZSET = 4;

    Integer STATE_YES = 1;
    Integer STATE_NO = 0;

    String DEFAULT_DATE_FORMATE = "yyyy-MM-dd hh:mm:ss";
}
