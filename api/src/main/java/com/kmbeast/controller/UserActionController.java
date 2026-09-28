package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.UserActionQueryDTO;
import com.kmbeast.pojo.entity.UserAction;
import com.kmbeast.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户行为操作控制器
 */
@RestController
@RequestMapping("/user-action")
public class UserActionController {

    @Autowired
    private UserActionService userActionService;

    /**
     * 用户行为操作新增
     * POST /api/v1.0/book-manage-api/user-action
     *
     * @param userAction 用户行为操作实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping
    public Result<Void> addAction(@RequestBody UserAction userAction) {
        userActionService.addAction(userAction);
        return ApiResult.success();
    }

    /**
     * 用户行为操作删除
     * DELETE /api/v1.0/book-manage-api/user-action/{id}
     *
     * @param id 用户行为操作主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @DeleteMapping("/{id}")
    public Result<Void> delAction(@PathVariable Integer id) {
        userActionService.delAction(id);
        return ApiResult.success();
    }

    /**
     * 收藏或取消收藏
     * POST /api/v1.0/book-manage-api/user-action/{booklist}/collection
     *
     * @param booklist 用户行为操作主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping("/{booklist}/collection")
    public Result<Void> collectionOperation(@PathVariable Integer booklist) {
        userActionService.collectionOperation(booklist);
        return ApiResult.success();
    }

    /**
     * "想看"操作
     * POST /api/v1.0/book-manage-api/user-action/{booklist}/like
     *
     * @param booklist 用户行为操作主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping("/{booklist}/like")
    public Result<Void> like(@PathVariable Integer booklist) {
        userActionService.likeView(booklist);
        return ApiResult.success();
    }

    /**
     * 用户行为操作查询
     * POST /api/v1.0/book-manage-api/user-action/query
     *
     * @param userActionQueryDTO 查询条件类
     * @return Result<List < UserAction>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/query")
    public Result<List<UserAction>> queryPage(@RequestBody UserActionQueryDTO userActionQueryDTO) {
        List<UserAction> userActions = userActionService.queryPage(userActionQueryDTO);
        Integer count = userActionService.queryPageCount(userActionQueryDTO);
        return ApiResult.success(userActions, count);
    }

}
