package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.exception.BusinessException;
import com.kmbeast.mapper.UserMapper;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.*;
import com.kmbeast.pojo.entity.User;
import com.kmbeast.pojo.enums.RoleEnum;
import com.kmbeast.pojo.enums.StatusEnum;
import com.kmbeast.pojo.vo.TokenResponseVO;
import com.kmbeast.pojo.vo.UserVO;
import com.kmbeast.service.UserService;
import com.kmbeast.utils.AssertUtils;
import com.kmbeast.utils.JwtUtil;
import com.kmbeast.utils.RoleValidUtils;
import com.kmbeast.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 用户服务实现类
 *
 * @author B站【程序员辰星】原创出品
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 用户注册
     *
     * @param userRegisterDTO 注册入参
     * @return Result<String> 响应结果
     * @author B站【程序员辰星】原创出品
     */
    @Override
    public Result<String> register(UserRegisterDTO userRegisterDTO) {
        // 参数校验 - 用户名非空校验，如果是空，抛出异常，程序不再往下执行
        AssertUtils.hasText(userRegisterDTO.getUsername(), "用户名不能为空");
        // 参数校验 - 账号非空校验，如果是空，抛出异常，程序不再往下执行
        AssertUtils.hasText(userRegisterDTO.getAccount(), "账号不能为空");
        // 参数校验 - 账号中文校验，如果是中文，抛出异常，程序不再往下执行
        AssertUtils.isFalse(ValidationUtils.containsChinese(userRegisterDTO.getAccount()), "账号不能包含中文");
        // 通过用户名去数据库用户表（user）查询用户信息
        User userEntity = baseMapper.getUserByUsername(userRegisterDTO.getUsername());
        // 校验 - 用户名不合法，如果是中文，抛出异常，程序不再往下执行
        AssertUtils.isTrue(userEntity == null, "用户名已经被使用，请换一个");
        // 通过账号查询用户信息
        User entity = baseMapper.getUserByAccount(userRegisterDTO.getAccount());
        // 校验 - 账号不合法，已经被别人占用，抛出异常，程序不再往下执行
        AssertUtils.isTrue(entity == null, "账号不可用");
        // 属性拷贝 UserRegister -> User
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        // 设置注册时间
        user.setCreateTime(LocalDateTime.now());
        // 设置初始角色 - 用户
        user.setRole(RoleEnum.USER.getRole());
        user.setLoginStatus(StatusEnum.LoginStatus.NORMAL.getCode());
        user.setSpeakStatus(StatusEnum.SpeakStatus.NORMAL.getCode());
        // 将注册信息存入数据库用户表（user）
        baseMapper.save(user);
        // 成功响应前端
        return ApiResult.success("注册成功");
    }

    /**
     * 用户登录
     *
     * @param userLoginDTO 登录入参
     * @return Result<String> 响应结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Object> login(UserLoginDto userLoginDTO) {
        // 参数校验 - 账号非空校验，如果是空，抛出异常，程序不再往下执行
        AssertUtils.hasText(userLoginDTO.getAccount(), "账号不能为空");
        // 参数校验 - 密码非空校验，如果是空，抛出异常，程序不再往下执行
        AssertUtils.hasText(userLoginDTO.getPassword(), "密码不能为空");
        // 通过账号查询用户信息
        // B站【程序员辰星】原创出品
        User user = baseMapper.getUserByAccount(userLoginDTO.getAccount());
        // 逻辑校验 - 通过账号找不到用户信息，证明用户没注册过，直接抛出异常，程序不再往下执行
        AssertUtils.isTrue(user != null, "账户不存在");
        if (Objects.nonNull(user.getLoginStatus())) {
            // 账号已经被封禁，无法登录
            AssertUtils.isTrue(!user.getLoginStatus(), "账户已被封禁");
        }
        // 逻辑校验 - 密码对比，如果数据库存储的密码跟用户登录传进来的不一致，直接抛出异常，程序不再往下执行
        AssertUtils.equals(userLoginDTO.getPassword(), user.getPassword(), "密码错误");
        // 调用JwtUtils工具类生成Token，存储用户ID、角色，后续Token检验成功，能够从Token里面解析出来在这里存储进去的用户ID、用户角色
        String token = JwtUtil.toToken(user.getId(), user.getRole());
        // 创建 TokenResponseVO 响应类
        TokenResponseVO tokenResponseVO = new TokenResponseVO(user.getId(), token, user.getRole());
        // 记录登录时间
        recordLastLoginTime(user.getId());
        // 成功响应
        return ApiResult.success("登录成功", tokenResponseVO);
    }

    @Async
    protected void recordLastLoginTime(Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setLastLoginTime(LocalDateTime.now());
        updateById(user);
    }

    /**
     * token检验
     *
     * @return Result<UserVO>
     */
    @Override
    public Result<UserVO> auth() {
        // 通过拦截器解析，本地线程已存储用户ID，凭借用户ID查询用户信息
        User user = baseMapper.getUserById(LocalThreadHolder.getUserId());
        // 属性拷贝： User -> UserVO
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        // 返回成功响应
        return ApiResult.success(userVO);
    }

    /**
     * 分页查询用户数据
     *
     * @param userQueryDto 分页参数
     * @return Result<List < UserVO>> 响应结果
     */
    @Override
    public Result<List<UserVO>> query(UserQueryDTO userQueryDto) {
        validIsAdmin();
        List<UserVO> userVOS = baseMapper.query(userQueryDto);
        Integer count = baseMapper.queryCount(userQueryDto);
        return ApiResult.success(userVOS, count);
    }

    private void validIsAdmin() {
        // 确保是管理员
        AssertUtils.isTrue(
                RoleEnum.ADMIN.getRole().equals(LocalThreadHolder.getRoleId()),
                "无修改权限"
        );
    }

    private void validIsUser(Integer userId) {
        // 确保是用户自己
        AssertUtils.isTrue(
                userId.equals(LocalThreadHolder.getUserId()),
                "无修改权限"
        );
    }

    /**
     * 用户信息修改
     *
     * @param userUpdateDTO 修改信息入参
     * @return Result<UserVO> 响应结果
     */
    @Override
    public Result<UserVO> update(UserUpdateDTO userUpdateDTO) {
        // 用户填写了邮箱。要检验号码格式
        if (StringUtils.hasText(userUpdateDTO.getEmail())) {
            if (!ValidationUtils.isValidEmail(userUpdateDTO.getEmail())) {
                return ApiResult.error("请填写正确邮箱");
            }
        }
        // 用户填写了号码。要检验号码格式
        if (StringUtils.hasText(userUpdateDTO.getPhone())) {
            // 国内号码
            AssertUtils.isTrue(
                    ValidationUtils.isValidMobile(userUpdateDTO.getPhone()),
                    "号码格式错误，请重新检查"
            );
        }
        User updateEntity = User.builder().id(LocalThreadHolder.getUserId()).build();
        BeanUtils.copyProperties(userUpdateDTO, updateEntity);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(updateEntity, userVO);
        baseMapper.update(updateEntity);
        return ApiResult.success(userVO);
    }

    /**
     * 修改密码
     *
     * @param userUpdatePasswordDto 密码入参
     * @return Result<String> 响应结果
     */
    @Override
    public Result<String> updatePassword(UserUpdatePasswordDTO userUpdatePasswordDto) {
        User user = baseMapper.getUserById(LocalThreadHolder.getUserId());
        AssertUtils.equals(
                userUpdatePasswordDto.getNewPassword(),
                userUpdatePasswordDto.getAgainPassword(),
                "前后密码输入不一致"
        );
        AssertUtils.equals(
                user.getPassword(),
                userUpdatePasswordDto.getOldPassword(),
                "原始密码验证失败"
        );
        user.setPassword(userUpdatePasswordDto.getNewPassword());
        baseMapper.update(user);
        return ApiResult.success("密码修改成功，请重新登录");
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     */
    @Override
    public Result<UserVO> getById(Integer id) {
        return Optional.ofNullable(baseMapper.getUserById(id))
                .map(user -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(user, userVO);
                    return userVO;
                })
                .map(ApiResult::success)
                .orElseThrow(() -> new BusinessException("用户不存在"));
    }

    /**
     * 后台新增用户
     *
     * @param userRegisterDTO 注册入参
     * @return Result<String> 响应结果
     */
    @Override
    public Result<String> save(UserRegisterDTO userRegisterDTO) {
        return register(userRegisterDTO);
    }

    /**
     * 用户修改自己的个人信息
     *
     * @param user 用户信息
     * @return Result<String>
     */
    @Override
    public Result<String> userUpdate(User user) {
        AssertUtils.notNull(user, "用户信息不能为空");
        AssertUtils.notNull(user.getId(), "用户ID不能为空");
        validIsUser(user.getId());
        return update(user);
    }

    /**
     * 管理员修改用户信息
     *
     * @param user 用户信息
     * @return Result<String>
     */
    @Override
    public Result<String> validUpdate(User user) {
        AssertUtils.notNull(user, "用户信息不能为空");
        AssertUtils.notNull(user.getId(), "用户ID不能为空");
        validIsAdmin();
        return update(user);
    }

    /**
     * 后台用户信息修改
     *
     * @param user 信息实体
     * @return Result<String> 响应结果
     */
    @Override
    public Result<String> update(User user) {
        // 用户名非空校验
        AssertUtils.isTrue(StringUtils.hasText(user.getUsername()), "用户名不能为空");
        // 账号非空校验
        AssertUtils.isTrue(StringUtils.hasText(user.getAccount()), "账号不能为空");
        // 填写了邮箱，要检验邮箱格式
        if (StringUtils.hasText(user.getEmail())) {
            if (!ValidationUtils.isValidEmail(user.getEmail())) {
                return ApiResult.error("请填写正确邮箱");
            }
        }
        // 填写了号码。要检验号码格式
        if (StringUtils.hasText(user.getPhone())) {
            // 国内号码
            AssertUtils.isTrue(
                    ValidationUtils.isValidMobile(user.getPhone()),
                    "号码格式错误，请重新检查"
            );
        }
        baseMapper.update(user);
        return ApiResult.success();
    }

    /**
     * 通过ID删除用户数据
     *
     * @param id 用户ID
     * @return Result<String>
     */
    @Override
    public Result<String> deleteById(Integer id) {
        AssertUtils.notNull(id, "ID不能为空");
        validIsAdmin();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);
        baseMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 修改状态
     *
     * @param user 用户信息
     * @return Result<String>
     */
    @Override
    public Result<String> status(User user) {
        RoleValidUtils.requestedAdmin("无操作权限");
        baseMapper.update(user);
        return ApiResult.success();
    }
}
