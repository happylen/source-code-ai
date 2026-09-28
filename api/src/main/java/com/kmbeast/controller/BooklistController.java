package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.BooklistQueryDTO;
import com.kmbeast.pojo.dto.BooklistSaveDTO;
import com.kmbeast.pojo.vo.BooklistDetailVO;
import com.kmbeast.pojo.vo.BooklistLendTopVO;
import com.kmbeast.pojo.vo.BooklistVO;
import com.kmbeast.service.BooklistService;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 书目控制器
 */
@RestController
@RequestMapping("/booklist")
public class BooklistController {

    @Resource
    private BooklistService booklistService;

    /**
     * 书目新增
     * POST /api/v1.0/book-manage-api/booklist
     *
     * @param booklistSaveDTO 书目实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping
    public Result<Void> addBooklist(@RequestBody BooklistSaveDTO booklistSaveDTO) {
        booklistService.addBooklist(booklistSaveDTO);
        return ApiResult.success();
    }

    /**
     * 查询书目详情
     * POST /api/v1.0/book-manage-api/{id}/detail
     *
     * @param id 书目ID
     * @return Result<BooklistDetailVO> 后台通用返回封装类
     */
    @GetMapping("/{id}/detail")
    public Result<BooklistDetailVO> detail(@PathVariable Integer id) {
        BooklistDetailVO booklistDetailVO = booklistService.detail(id);
        return ApiResult.success(booklistDetailVO);
    }

    /**
     * 书目修改
     * POST /api/v1.0/book-manage-api/booklist/update
     *
     * @param booklistSaveDTO 书目实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping("/update")
    public Result<Void> updateBooklist(@RequestBody BooklistSaveDTO booklistSaveDTO) {
        booklistService.updateBooklist(booklistSaveDTO);
        return ApiResult.success();
    }

    /**
     * 书目删除
     * DELETE /api/v1.0/book-manage-api/booklist/{id}
     *
     * @param id 书目主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @DeleteMapping("/{id}")
    public Result<Void> delBooklist(@PathVariable Integer id) {
        booklistService.delBooklist(id);
        return ApiResult.success();
    }

    /**
     * 书目查询
     * POST /api/v1.0/book-manage-api/booklist/query
     *\
     *      * @param booklistQueryDTO 查询条件类
     * @return Result<List < BooklistVO>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/query")
    public Result<List<BooklistVO>> queryPage(@RequestBody BooklistQueryDTO booklistQueryDTO) {
        List<BooklistVO> booklists = booklistService.queryPage(booklistQueryDTO);
        Integer count = booklistService.queryPageCount(booklistQueryDTO);
        return ApiResult.success(booklists, count);
    }

    /**88
     * 查询借阅最多的书目
     * POST /api/v1.0/book-manage-api/booklist/lendTop/{count}
     *
     * @param count 查询条数
     * @return Result<List < BooklistVO>> 后台通用返回封装类
     */
    @GetMapping("/lendTop/{count}")
    public Result<List<BooklistVO>> lendTop(@PathVariable Integer count) {
        List<BooklistVO> booklists = booklistService.lendTop(count);
        return ApiResult.success(booklists);
    }

    /**
     * 查询借阅最多的书目 - 柱状图
     * POST /api/v1.0/book-manage-api/booklist/lendTopBar/{count}
     *
     * @param count 查询条数
     * @return Result<List < BooklistVO>> 后台通用返回封装类
     */
    @GetMapping("/lendTopBar/{count}")
    public Result<List<BooklistLendTopVO>> lendTopBar(@PathVariable Integer count) {
        List<BooklistLendTopVO> booklistLendTopVOS = booklistService.booklistLendTopVOS(count);
        return ApiResult.success(booklistLendTopVOS);
    }

    /**
     * 查询用户收藏的书目信息
     * POST /api/v1.0/book-manage-api/booklist/collection
     *
     * @param booklistQueryDTO 查询条件类
     * @return Result<List < BooklistVO>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/collection")
    public Result<List<BooklistVO>> collection(@RequestBody BooklistQueryDTO booklistQueryDTO) {
        List<BooklistVO> booklists = booklistService.collection(booklistQueryDTO);
        if (CollectionUtils.isEmpty(booklists)) {
            return ApiResult.success(new ArrayList<>(), 0);
        }
        Integer count = booklistService.queryPageCount(booklistQueryDTO);
        return ApiResult.success(booklists, count);
    }

    /**
     * 查询向用户推荐的图书 - 协同过滤算法推荐
     * GET /api/v1.0/book-manage-api/booklist/{count}/recommend
     *
     * @param count 推荐本数
     * @return Result<List < BooklistVO>> 后台通用返回封装类
     */
    @GetMapping("/{count}/recommend")
    public Result<List<BooklistVO>> recommend(@PathVariable Integer count) {
        List<BooklistVO> booklistVOS = booklistService.recommend(count);
        return ApiResult.success(booklistVOS);
    }

}
