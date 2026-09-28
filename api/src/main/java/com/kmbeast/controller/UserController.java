package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.UserLoginDto;
import com.kmbeast.pojo.dto.UserQueryDTO;
import com.kmbeast.pojo.dto.UserRegisterDTO;
import com.kmbeast.pojo.dto.UserUpdatePasswordDTO;
import com.kmbeast.pojo.entity.User;
import com.kmbeast.pojo.vo.UserVO;
import com.kmbeast.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 * @author B站【程序员辰星】原创出品
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userLoginDTO 登录入参
     * @return Result<String> 响应结果
     */
    @PostMapping(value = "/login")
    public Result<Object> login(@RequestBody UserLoginDto userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    /**
     * token校验
     * 前端检验token，token在请求头中
     * JwtInterceptor 拦截器拦截解析token，可获取登录用户ID、角色ID
     * 如果检验成功，凭着用户ID查询用户信息，返回至前端
     */
    @GetMapping(value = "/auth")
    public Result<UserVO> auth() {
        return userService.auth();
    }


    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return Result<UserVO>
     */
    @GetMapping(value = "/getById/{id}")
    public Result<UserVO> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }


    /**
     * 用户注册
     *
     * @param userRegisterDTO 注册入参
     * @return Result<String> 响应结果
     */
    @PostMapping(value = "/register")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }

    /**
     * 后台新增用户
     *
     * @param userRegisterDTO 注册入参
     * @return Result<String> 响应结果
     */
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.save(userRegisterDTO);
    }

    /**
     * 用户信息修改
     *
     * @param user 修改信息入参
     * @return Result<String> 响应结果
     */
    @PutMapping(value = "/userUpdate")
    public Result<String> userUpdate(@RequestBody User user) {
        return userService.userUpdate(user);
    }

    /**
     * 管理员修改用户信息
     *
     * @param user 修改信息入参
     * @return Result<String> 响应结果
     */
    @PutMapping()
    public Result<String> update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * 修改状态
     *
     * @param user 修改信息入参
     * @return Result<String> 响应结果
     */
    @PutMapping("/status")
    public Result<String> status(@RequestBody User user) {
        return userService.status(user);
    }


    /**
     * 后台用户信息修改
     *
     * @param user 信息实体
     * @return Result<String> 响应结果
     */
    @PutMapping(value = "/validUpdate")
    public Result<String> validUpdate(@RequestBody User user) {
        return userService.validUpdate(user);
    }

    /**
     * 用户修改密码
     *
     * @param userUpdatePasswordDto 修改信息入参
     * @return Result<String> 响应结果
     */
    @PutMapping(value = "/updatePassword")
    public Result<String> updatePassword(@RequestBody UserUpdatePasswordDTO userUpdatePasswordDto) {
        return userService.updatePassword(userUpdatePasswordDto);
    }

    /**
     * 通过ID删除用户信息
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> deleteById(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    /**
     * 查询用户数据
     *
     * @param userQueryDto 查询参数
     * @return Result<List < UserVO>> 响应结果
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<UserVO>> query(@RequestBody UserQueryDTO userQueryDto) {
        return userService.query(userQueryDto);
    }

}

