package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.NoticeQueryDTO;
import com.kmbeast.pojo.dto.UserActionQueryDTO;
import com.kmbeast.pojo.entity.Notice;
import com.kmbeast.pojo.entity.UserAction;
import com.kmbeast.pojo.vo.NoticeListVO;
import com.kmbeast.pojo.vo.ScoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户行为操作持久化接口
 */
@Mapper
public interface UserActionMapper extends BaseMapper<UserAction> {

    List<UserAction> queryPage(UserActionQueryDTO userActionQueryDTO);

    Integer queryPageCount(UserActionQueryDTO userActionQueryDTO);

    List<ScoreVO> scoreList();

}
