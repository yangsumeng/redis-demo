package com.yangsm.demo.redis.controller;

import com.yangsm.demo.redis.lock.RedisLock;
import com.yangsm.demo.redis.service.RedisService;
import com.yangsm.demo.redis.util.ResponseEx;
import com.yangsm.demo.redis.vo.RedisParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 换存测试
 *
 */

@Slf4j
@RestController
@RequestMapping("")
@Api(value = "分布式锁测试")
public class LockController {

    @ApiOperation(value = "lock", notes = "lock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true , dataType = "String" , paramType = "query"),
            @ApiImplicitParam(name = "value", value = "value", required = true , dataType = "String" , paramType = "query"),
            @ApiImplicitParam(name = "timeOut", value = "timeOut", required = true , dataType = "Long" , paramType = "query")
    })
    @GetMapping("/lock")
    public ResponseEx<Object> lock(String key ,String value,Long timeOut) {
        boolean result = RedisLock.lock(key,value,timeOut);
        log.info("-----------------------lock-----结果："+result);
        return ResponseEx.createSuccess();
    }

    @ApiOperation(value = "unLock", notes = "unLock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true , dataType = "String" , paramType = "query"),
            @ApiImplicitParam(name = "value", value = "value", required = true , dataType = "String" , paramType = "query")
    })
    @GetMapping("/unLock")
    public ResponseEx<Object> unLock(String key ,String value) {
        boolean result = RedisLock.unLock(key,value);
        log.info("-----------------------unLock-----结果："+result);
        return ResponseEx.createSuccess("成功");
    }

    @ApiOperation(value = "保存", notes = "保存")
    @GetMapping("/test")
    public ResponseEx<Object> test(@RequestBody RedisParams redisParams) throws IOException {

        log.info("-----------------------unLock-----结果：");
        return ResponseEx.createSuccess("成功");
    }

}
