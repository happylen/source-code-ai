package com.kmbeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.*;
import com.kmbeast.pojo.entity.User;
import com.kmbeast.pojo.vo.UserVO;

import java.util.List;

/**
 * 用户服务类
 */
public interface UserService extends IService<User> {

    Result<String> register(UserRegisterDTO userRegisterDTO);

    Result<Object> login(UserLoginDto userLoginDTO);

    Result<UserVO> auth();

    Result<List<UserVO>> query(UserQueryDTO userQueryDto);

    Result<String> updatePassword(UserUpdatePasswordDTO userUpdatePasswordDto);

    Result<UserVO> getById(Integer id);

    Result<UserVO> update(UserUpdateDTO userUpdateDTO);

    Result<String> save(UserRegisterDTO userRegisterDTO);

    Result<String> update(User user);

    Result<String> userUpdate(User user);

    Result<String> validUpdate(User user);

    Result<String> deleteById(Integer id);

    Result<String> status(User user);

}
