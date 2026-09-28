package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.UserQueryDTO;
import com.kmbeast.pojo.entity.User;
import com.kmbeast.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久化接口
 */
public interface UserMapper extends BaseMapper<User> {

    int save(User userInsert);

    List<UserVO> query(UserQueryDTO userQueryDto);

    int queryCount(UserQueryDTO userQueryDto);

    int update(User user);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    User getUserById(@Param(value = "id") Integer id);

    User getUserByAccount(@Param(value = "account") String account);

    User getUserByUsername(@Param(value = "username") String account);

}
