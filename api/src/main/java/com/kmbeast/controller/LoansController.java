package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.LoansQueryDTO;
import com.kmbeast.pojo.entity.Loans;
import com.kmbeast.pojo.vo.LoansVO;
import com.kmbeast.service.LoansService;
import com.kmbeast.utils.RoleValidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 借阅记录控制器
 */
@RestController
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoansService loansService;

    /**
     * 借阅记录新增
     * POST /api/v1.0/book-manage-api/loans
     *
     * @param loans 借阅记录实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping
    public Result<Void> addLoans(@RequestBody Loans loans) {
        loansService.addLoans(loans);
        return ApiResult.success();
    }

    /**
     * 借阅记录修改
     * POST /api/v1.0/book-manage-api/loans/update
     *
     * @param loans 借阅记录实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping("/updateLoans")
    public Result<Void> updateLoans(@RequestBody Loans loans) {
        loansService.updateLoans(loans);
        return ApiResult.success();
    }

    /**
     * 借阅记录删除
     * DELETE /api/v1.0/book-manage-api/loans/{id}
     *
     * @param id 借阅记录主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @DeleteMapping("/{id}")
    public Result<Void> delCategory(@PathVariable Integer id) {
        loansService.delCategory(id);
        return ApiResult.success();
    }

    /**
     * 借阅记录查询
     * POST /api/v1.0/book-manage-api/loans/query
     *
     * @param loansQueryDTO 查询条件类
     * @return Result<List < loansVOS>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/query")
    public Result<List<LoansVO>> queryPage(@RequestBody LoansQueryDTO loansQueryDTO) {
        RoleValidUtils.requestedAdmin("无操作权限");
        List<LoansVO> loansVOS = loansService.queryPage(loansQueryDTO);
        Integer count = loansService.queryPageCount(loansQueryDTO);
        return ApiResult.success(loansVOS, count);
    }

    /**
     * 还书
     * POST /api/v1.0/book-manage-api/loans/returnBook
     *
     * @param id 借阅记录主键ID
     * @return Result<List < loansVOS>> 后台通用返回封装类
     */
    @PostMapping("/returnBook/{id}")
    public Result<List<LoansVO>> returnBook(@PathVariable Integer id) {
        loansService.returnBook(id);
        return ApiResult.success();
    }

    /**
     * 查询用户的借阅记录
     * POST /api/v1.0/book-manage-api/loans/query
     *
     * @param loansQueryDTO 查询条件类
     * @return Result<List < loansVOS>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/queryUser")
    public Result<List<LoansVO>> queryUser(@RequestBody LoansQueryDTO loansQueryDTO) {
        loansQueryDTO.setUserId(LocalThreadHolder.getUserId());
        List<LoansVO> loansVOS = loansService.queryPage(loansQueryDTO);
        Integer count = loansService.queryPageCount(loansQueryDTO);
        return ApiResult.success(loansVOS, count);
    }

    /**
     * 查询用户名下借书或逾期未还数量
     * POST /api/v1.0/book-manage-api/loans/lendOrOverdueCount
     *
     * @return Result<Integer> 后台通用返回封装类
     */
    @GetMapping("/lendOrOverdueCount")
    public Result<Integer> lendOrOverdueCount() {
        Integer count = loansService.lendOrOverdueCount();
        return ApiResult.success(count);
    }

}
