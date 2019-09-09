
package com.yangsm.demo.redis.controller;

import com.yangsm.demo.redis.service.UserService;
import com.yangsm.demo.redis.util.DateUtils;
import com.yangsm.demo.redis.util.ResponseEx;
import com.yangsm.demo.redis.vo.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;



/** auto code version 1.4
 * <p>
 * 用户表 前端控制器
 * </p>
 * @since 2018-09-03
 */
@RestController
@RequestMapping("/user")
@Api(value = "user")
@CacheConfig(cacheNames = "user")
public class UserController {
	@Autowired
	private UserService userService;

	/**自动生成 2018-09-03*/
	@ApiOperation(value = "按照userID获取详情", notes = "获取user详细信息")
	@ApiImplicitParam(name = "userId", value = "主键ID", required = true, dataType = "String", paramType = "path")
	@GetMapping("/byId/{userId}")
	/*
	 *	key="'id_'+#userId"  或者  key="#p0"   （#user.id）
	 *  不设置key 取默认值	RedisCacheConfig方法中的 keyGenerator
	 *  也可指定keyGenerator 与 key 互斥
	 *  cacheNames="user" 可省略 区CacheConfig中的cacheNames
	 *  Cacheable中的设置要与 CachePut CacheEvict 配合
	 */
	//@Cacheable //不设置key 取默认值  也可以写成取第一个参数key="#p0"
	@Cacheable(key="'user_id_' + #p0",unless="!#result.success or #result.data == null")
	public ResponseEx<Object> getById(@PathVariable String userId) {
	    //构造测试数据
		User user = new User(userId,"名称"+userId,100 , DateUtils.getCurrentFormatDate("yyyy-MM-dd HH:mm"));
        return ResponseEx.createSuccess(user);
	}


	/**自动生成 2018-09-03*/
	@ApiOperation(value="新增user")
	@PostMapping("/saveUser")
	@CachePut(key="#user.getId()")
	public ResponseEx<Object> saveUser(@RequestBody @ApiParam(name="param",value="参数JSON",required=true) User user, @RequestHeader HttpHeaders headers) {
	    return ResponseEx.createSuccess(user);
	}

	@ApiOperation(value="编辑user")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "URL参数：userID", required = true)
	})
	@PostMapping("/editUser/{userId}")
	@CacheEvict(key="'user_id_' + #p1")//#userId  修改清楚缓存
	public ResponseEx<Object> editUser(@RequestBody @ApiParam(name="param",value="参数JSON",required=true) User user, @PathVariable String userId, @RequestHeader HttpHeaders headers) {
        return ResponseEx.createSuccess("success");
	}

	/**自动生成 2018-09-03*/
	@ApiOperation(value = "通过主键ID逻辑删除", notes = "逻辑删除用户表实体")
	@ApiImplicitParam(name = "userId", value = "主键ID", required = true, dataType = "String", paramType = "path")
	@GetMapping("/deleteById/{userId}")
	@CacheEvict(key="'user_id_' + #p0")
	public ResponseEx<Object> deleteById(@PathVariable String userId) {
//        boolean ok = userService.deleteById(Long.valueOf(userId));
//        if(!ok){
//        	return ResponseEx.createError("删除失败");
//        }
        return ResponseEx.createSuccess("删除成功");
     }

	/**自动生成 2018-09-03 */
	@ApiOperation(value = "clearAllUserCache 清空所有user缓存方法")
	@GetMapping("/clearAllUserCache")
	@CacheEvict(allEntries=true)
	public ResponseEx<Object> clearAllUserCache(){
        return ResponseEx.createSuccess("成功");
	}
}
