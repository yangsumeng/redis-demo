package com.yangsm.demo.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户实体
 *
 * @author: yangsm
 *
 * @create: 2019-09-09 16:20
 **/
@Data
@AllArgsConstructor
public class User implements Serializable {
    private String id;

    private String name;

    private int age;

    private String date;

}
