package com.kmbeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kmbeast.pojo.dto.UserActionQueryDTO;
import com.kmbeast.pojo.entity.UserAction;
import com.kmbeast.pojo.vo.ScoreVO;

import java.util.List;

/**
 * 用户行为操作业务逻辑接口
 */
public interface UserActionService extends IService<UserAction> {

    void addAction(UserAction userAction);

    void delAction(Integer id);

    List<UserAction> queryPage(UserActionQueryDTO userActionQueryDTO);

    Integer queryPageCount(UserActionQueryDTO userActionQueryDTO);

    void collectionOperation(Integer booklistId);

    void likeView(Integer booklistId);

    List<ScoreVO> scores();

}
