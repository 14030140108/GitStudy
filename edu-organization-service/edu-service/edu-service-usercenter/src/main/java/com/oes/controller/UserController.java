package com.oes.controller;


import com.alibaba.fastjson.JSONObject;
import com.oes.holder.LoginUserHolder;
import com.oes.holder.UserDTO;
import com.oes.model.dto.BaseResultDTO;

import com.oes.model.entity.User;
import com.oes.model.query.user.UserRegisterQuery;
import com.oes.model.query.user.UserUpdateQuery;
import com.oes.model.vo.user.UserVo;
import com.oes.service.UserService;

import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
@Api(tags = {"普通用户"})
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation("用户注册")
    public HttpResult register(@RequestBody UserRegisterQuery query) {
        log.info("-----------用户注册-------");
        BaseResultDTO resultDTO = userService.register(query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/user")
    public HttpResult updateUser(@RequestBody UserUpdateQuery query) {
        BaseResultDTO resultDTO = userService.update(query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/user/{id}")
    public HttpResult queryUser(@ApiParam(value = "用户id", example = "1") @PathVariable String id) {
        UserVo user = userService.queryById(Long.valueOf(id));
        if (user != null) {
            return HttpResult.ok("查询成功", user);
        } else return HttpResult.error(HttpStatus.SC_BAD_REQUEST, "查询失败");
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("/user/{id}")
    public HttpResult deleteUser(@ApiParam(value = "用户id", example = "1") @PathVariable String id) {
        return userService.deleteUser(Long.valueOf(id));
    }

    @ApiOperation("查询是否认证")
    @GetMapping("/user/Authentication")
    public HttpResult verifyAuthentication(long userid) {
        return userService.verifyAuthentication(userid);
    }

    @ApiOperation("查询用户名是否存在")
    @GetMapping("/user/account/repetition")
    public HttpResult checkAccount(String account) {
        if (userService.checkedAccount(account)) return HttpResult.ok("用户名可用");
        else return HttpResult.error(HttpStatus.SC_BAD_REQUEST, "用户名已存在");
    }


    @GetMapping("/user/phone/repetition")
    @ApiOperation("手机是否已注册")
    public HttpResult checkPhone(@RequestParam String phone) {
        BaseResultDTO baseResultDTO = userService.checkedPhone(phone);
        if (baseResultDTO.isSuccess()) {
            return HttpResult.ok(baseResultDTO.getMessage());
        } else {
            return HttpResult.error(baseResultDTO.getMessage());
        }
    }

    /*******************对服务间暴露API**********************************/
    @PostMapping("/user/api/{roleName}")
    @ApiOperation("用户注册")
    public HttpResult insert(@RequestBody User user, @PathVariable String roleName) {
        log.info("-----------服务间模块调用用户插入-------");
        return userService.insert(user, roleName);
    }

    //courseContrller
    //teacher
    @GetMapping("/user/auth/api/{userId}")
    public HttpResult getUserAuthInfo(@PathVariable Integer userId) {
        return userService.getUserAuthInfoById(userId);
    }

    @PostMapping("/user/api/roleName")
    public boolean addRoles(@RequestBody JSONObject object) {
        int userId = (int) object.get("userId");
        String roleName = object.getString("roleName");
        return userService.addRoles(userId, roleName);
    }

    @GetMapping("user/api/roleId")
    public boolean addRoleById(@PathVariable Integer userId, @PathVariable Integer roleId) {
        return userService.addRole(userId, roleId);
    }

    @DeleteMapping("user/api/roleName")
    public HttpResult removeRole(@PathVariable Integer userId, @PathVariable String roleName) {
        return userService.removeRole(userId, roleName);
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/user/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }

}
