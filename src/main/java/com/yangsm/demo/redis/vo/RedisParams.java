package com.yangsm.demo.redis.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RedisParams" , description = "redisParams")
public class RedisParams {

    @ApiModelProperty(value="namespace",name="命名空间")
    private String namespace;

    @ApiModelProperty(value="key",name="key值")
    private String key;

    @ApiModelProperty(value="field",name="字段only for 1-Hash")
    private String field; // only for Hash

    @ApiModelProperty(value="value",name="value值")
    private JSONObject value;

    @ApiModelProperty(value="type",name="类型0-String，1-Hash，2-List，3-Set，4-ZSet")
    private Integer type; // 数据类型：0-String，1-Hash，2-List，3-Set，4-ZSet

    @ApiModelProperty(value="score",name="排序only for 4-ZSet")
    private Integer score; // only for ZSet

}
