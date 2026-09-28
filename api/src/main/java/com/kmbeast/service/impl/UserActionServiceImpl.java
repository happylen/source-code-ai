package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.mapper.UserActionMapper;
import com.kmbeast.pojo.dto.UserActionQueryDTO;
import com.kmbeast.pojo.entity.UserAction;
import com.kmbeast.pojo.enums.UserActionTypeEnum;
import com.kmbeast.pojo.vo.ScoreVO;
import com.kmbeast.service.UserActionService;
import com.kmbeast.utils.AssertUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户行为操作业务逻辑实现类
 * type 1 - 收藏（收藏 OR 取消收藏） -> 新增/删除
 * type 2 - 点击 -> 新增（无次数限制的新增）
 * type 3 - 想看 -> 新增（针对具体的书目，只能新增一次）
 * type 4 - 停留时长 -> 新增（无次数限制的新增）
 */
@Service
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {

    /**
     * 参数校验
     *
     * @param userAction 行为操作实体类
     */
    private void validParams(UserAction userAction) {
        AssertUtils.notNull(userAction, "参数项不为空");
        AssertUtils.notNull(userAction.getUserId(), "用户ID不为空");
        AssertUtils.notNull(userAction.getBooklistId(), "书目ID不为空");
        AssertUtils.notNull(userAction.getType(), "行为类型不为空");
    }

    /**
     * 行为操作新增
     *
     * @param userAction 行为操作
     */
    @Override
    public void addAction(UserAction userAction) {
        userAction.setUserId(LocalThreadHolder.getUserId());
        validParams(userAction);
        userAction.setCreateTime(LocalDateTime.now());
        save(userAction);
    }

    @Override
    public void delAction(Integer id) {
        AssertUtils.notNull(id, "ID不为空");
        removeById(id);
    }

    @Override
    public List<UserAction> queryPage(UserActionQueryDTO userActionQueryDTO) {
        userActionQueryDTO.setUserId(LocalThreadHolder.getUserId());
        return this.baseMapper.queryPage(userActionQueryDTO);
    }

    @Override
    public Integer queryPageCount(UserActionQueryDTO userActionQueryDTO) {
        return this.baseMapper.queryPageCount(userActionQueryDTO);
    }

    /**
     * 收藏操作 （收藏 OR 取消收藏） -> 新增/删除
     *
     * @param booklistId 书目ID
     */
    @Override
    public void collectionOperation(Integer booklistId) {
        AssertUtils.notNull(booklistId, "书目ID不为空");

        Integer targetTypeCount = getTargetTypeCount(booklistId, UserActionTypeEnum.STATUS_1.getStatus());
        if (targetTypeCount > 0) { // 用户已经收藏过了，此处直接删除即可 - 取消收藏
            remove(
                    new LambdaUpdateWrapper<UserAction>()
                            .eq(UserAction::getUserId, LocalThreadHolder.getUserId())
                            .eq(UserAction::getBooklistId, booklistId)
                            .eq(UserAction::getType, UserActionTypeEnum.STATUS_1.getStatus())
            );
            return;
        }
        UserAction userAction = createUserAction(booklistId, UserActionTypeEnum.STATUS_1.getStatus());
        addAction(userAction);
    }

    private Integer getTargetTypeCount(Integer booklistId,Integer type) {
        UserActionQueryDTO userActionQueryDTO = new UserActionQueryDTO();
        userActionQueryDTO.setBooklistId(booklistId);
        userActionQueryDTO.setUserId(LocalThreadHolder.getUserId());
        userActionQueryDTO.setType(type);
        return queryPageCount(userActionQueryDTO);
    }

    @Override
    public void likeView(Integer booklistId) {
        AssertUtils.notNull(booklistId, "书目ID不为空");
        Integer targetTypeCount = getTargetTypeCount(booklistId, UserActionTypeEnum.STATUS_3.getStatus());
        if (targetTypeCount > 0) {
            return;
        }
        UserAction userAction = createUserAction(booklistId, UserActionTypeEnum.STATUS_3.getStatus());
        addAction(userAction);
    }

    /**
     * 构建行为操作实体
     * @param booklistId 书目ID
     * @param type 类型
     * @return UserAction
     */
    private UserAction createUserAction(Integer booklistId,Integer type){
        UserAction userAction = new UserAction();
        userAction.setBooklistId(booklistId);
        userAction.setType(type);
        return userAction;
    }

    @Override
    public List<ScoreVO> scores() {
        return this.baseMapper.scoreList();
    }
}
