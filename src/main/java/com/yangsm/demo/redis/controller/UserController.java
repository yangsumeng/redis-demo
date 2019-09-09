
package com.yangsm.demo.redis.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** auto code version 1.4
 * <p>
 * 用户表 前端控制器
 * </p>
 * @author JiangP
 * @since 2018-09-03
 */
@RestController
@RequestMapping("/user")
@Api(value = "user" , description = "用户表")
@CacheConfig(cacheNames = "user")
public class UserController {
//	@Autowired
//	private UserService userService;
//
//	/**自动生成 2018-09-03*/
//	@ApiOperation(value = "按照userID获取详情", notes = "获取user详细信息")
//	@ApiImplicitParam(name = "userId", value = "主键ID", required = true, dataType = "String", paramType = "path")
//	@GetMapping("/byId/{userId}")
//	/*
//	 *	key="'id_'+#userId"  或者  key="#p0"   （#user.id）
//	 *  不设置key 取默认值	RedisCacheConfig方法中的 keyGenerator
//	 *  也可指定keyGenerator 与 key 互斥
//	 *  cacheNames="user" 可省略 区CacheConfig中的cacheNames
//	 *  Cacheable中的设置要与 CachePut CacheEvict 配合
//	 */
//	//@Cacheable //不设置key 取默认值  也可以写成取第一个参数key="#p0"
//	@Cacheable(key="'user_id_' + #p0",unless="!#result.success or #result.data == null")
//	public ResponseEx<Object> getById(@PathVariable String userId) {
//
//		User user = userService.selectById(userId);
//        return ResponseEx.createSuccess(user);
//	}
//
//	/**自动生成 2018-09-03*/
//	@ApiOperation(value="获取分页数据")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "pageSize", value = "URL参数：每页显示数量", required = true, paramType = "path"),
//			@ApiImplicitParam(name = "pageIndex", value = "URL参数：当前页", required = true, paramType = "path")
//	})
//	@PostMapping("/pageList/{pageSize}/{pageIndex}")
//	public ResponsePageEx<Object> selectMapList(@PathVariable int pageSize, @PathVariable int pageIndex,
//												@RequestBody @ApiParam(name="params",value="搜索条件JSON参数，无参请传{}",required=true) Map<String, Object> params) {
//        ResponsePageEx<Object>  result = ResponsePageEx.createError("校验未通过");
//
//		if(null == params.get("state")){
//			params.put("state", Constance.STATE_YES); //默认查询生效
//		}
//
//        if(pageSize > 0 && pageIndex > 0){
//			Page<Map<String,Object>> page = userService.selectMapList(new Page<>(pageIndex, pageSize), params);
//			result =  ResponsePageEx.createSuccess(page, page.getTotal(), page.getCurrent(), page.getSize(), page.getPages());
//        }
//		return result;
//	}
//
//	/**自动生成 2018-09-03*/
//	@ApiOperation(value="新增user")
//	@PostMapping("/saveUser")
////	@CachePut(key="#user.getUserName()")
//	public ResponseEx<Object> saveUser(@RequestBody @ApiParam(name="param",value="参数JSON",required=true) User user, @RequestHeader HttpHeaders headers) {
//		//String userAccount = headers.getFirst(Constants.USER_HEADER);
//		user.setCreator(user.getOperator());
//		user.setOperateDate(new Date());
//		user.setCreateDate(new Date());
//		user.setState(Constance.STATE_YES);
//		boolean result = userService.insert(user);
//		if (!result) {
//			return ResponseEx.createError("新增失败");
//		}
//		return ResponseEx.createSuccess("success");
//	}
//
//	@ApiOperation(value="编辑user")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "userId", value = "URL参数：userID", required = true)
//	})
//	@PostMapping("/editUser/{userId}")
//	@CacheEvict(key="'user_id_' + #p1")//#userId  修改清楚缓存
//	public ResponseEx<Object> editUser(@RequestBody @ApiParam(name="param",value="参数JSON",required=true) User user, @PathVariable String userId, @RequestHeader HttpHeaders headers) {
//		// 设置主键ID
//        //String userAccount = headers.getFirst(Constants.USER_HEADER);
//		user.setUserId(Long.valueOf(userId));
//		user.setOperateDate(new Date());
//		boolean result = userService.updateById(user);
//        if (!result) {
//        	return ResponseEx.createError("修改失败");
//        }
//        return ResponseEx.createSuccess("success");
//	}
//
//	/**自动生成 2018-09-03*/
//	@ApiOperation(value = "通过主键ID逻辑删除", notes = "逻辑删除用户表实体")
//	@ApiImplicitParam(name = "userId", value = "主键ID", required = true, dataType = "String", paramType = "path")
//	@GetMapping("/deleteById/{userId}")
//	@CacheEvict(key="'user_id_' + #p0")
//	public ResponseEx<Object> deleteById(@PathVariable String userId) {
//        boolean ok = userService.deleteById(Long.valueOf(userId));
//        if(!ok){
//        	return ResponseEx.createError("删除失败");
//        }
//        return ResponseEx.createSuccess("删除成功");
//     }
//
//    /**自动生成 2018-09-03 */
//    @ApiOperation(value = "获取用户表页面选择框数据", notes = "获取用户表页面选择框数据")
//    @PostMapping("/getLabelAndValueList")
//    public ResponseEx<Object> getLabelAndValueList(@RequestBody @ApiParam(name="params",value="无参请传{}")  Map<String,Object> params) {
//		List<Map<String,Object>> list = userService.getLabelAndValueList(params);
//		return ResponseEx.createSuccess(list);
//	}
//
//
//	/**自动生成 2018-09-03 */
//	@ApiOperation(value = "clearAllUserCache 清空所有user缓存方法")
//	@GetMapping("/clearAllUserCache")
//	@CacheEvict(allEntries=true)
//	public ResponseEx<Object> clearAllUserCache(){
//        return ResponseEx.createSuccess("成功");
//	}
//
//	/**自动生成 2018-09-03 */
//	@ApiOperation(value = "emptyMethod2 方法")
//	@GetMapping("/emptyMethod2")
//	public ResponseEx<Object> emptyMethod2(){
//        Map<String,Object> params = null;
//	    Object result = userService.emptyMethod2(params);
//        return ResponseEx.createSuccess("成功");
//	}
//
//	@ApiOperation(value = "获取登录信息", notes = "获取登录信息")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "userAccount", value = "用户账户", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "password", value = "密码", required = true,dataType = "String", paramType = "query"),
//	})
//	@GetMapping("/getLoginInfo")
//    public ResponseEx<Object> getLoginInfo(@RequestParam("userAccount") String userAccount,
//    		@RequestParam("password") String password){
//    	String json = ResponseEx.createSuccess().toJson();
//    	if ((userAccount != null && password != null)
//				&& (!"".equals(userAccount) && !"".equals(password))) {
//    		User user = this.userService.getByAccount(userAccount);
//    		if (user != null) {
//    			// 校验密码
//    			if(!validationLoginUser(user, password)) {
//    				return ResponseEx.createException("登陆失败，用户名或密码错误！");
//    			}
//    			Map<String,Object> map = new HashMap<String,Object>();
//    			map.put("userId", user.getUserId());
//    			map.put("companyId", user.getCompanyId());
//    			map.put("userName", user.getUserName());
//    			return ResponseEx.createSuccess("登录成功",map);
//
//    		}else {
//    			return ResponseEx.createException("登陆失败，用户名或密码错误！");
//    		}
//    	}else {
//    		return ResponseEx.createException("用户名或密码为空");
//    	}
//    }
//
//    private Boolean validationLoginUser(User userInfo, String comparePwd)
//    {
//        Boolean result = false;
//        try
//        {
//            if (userInfo == null || comparePwd == null || "".equals(comparePwd))
//            {
//                result = false;
//            }
//            else
//            {
//                String pwd = userInfo.getPassword();
//                String md5comparePwd = new com.ebei.eba.common.util.MD5().getMD5ofStr(comparePwd);
//                if ( pwd != null && !"".equals(pwd))
//                {
//                    if (pwd.equals(comparePwd) || pwd.equals(md5comparePwd))
//                    {
//                        result = true;
//                    }
//                    else
//                    {
//                        result = false;
//                    }
//                }
//                else
//                {
//                    result = false;
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            result = false;
//            e.printStackTrace();
//        }
//        return result;
//    }
//
	}
