package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.BookItemQueryDTO;
import com.kmbeast.pojo.entity.BookItem;
import com.kmbeast.pojo.vo.BookItemVO;
import com.kmbeast.service.BookItemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 馆藏书目控制器
 */
@RestController
@RequestMapping("/book-item")
public class BookItemController {

    @Resource
    private BookItemService bookItemService;

    /**
     * 馆藏书目新增
     * POST /api/v1.0/book-manage-api/book-item
     *
     * @param bookItem 馆藏书目实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping
    public Result<Void> addBookItem(@RequestBody BookItem bookItem) {
        bookItemService.addBookItem(bookItem);
        return ApiResult.success();
    }

    /**
     * 馆藏书目修改
     * POST /api/v1.0/book-manage-api/book-item/update
     *
     * @param bookItem 馆藏书目实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping("/update")
    public Result<Void> updateBookItem(@RequestBody BookItem bookItem) {
        bookItemService.updateBookItem(bookItem);
        return ApiResult.success();
    }

    /**
     * 馆藏书目删除
     * DELETE /api/v1.0/book-manage-api/book-item/{id}
     *
     * @param id 馆藏书目主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @DeleteMapping("/{id}")
    public Result<Void> delBookItem(@PathVariable Integer id) {
        bookItemService.delBookItem(id);
        return ApiResult.success();
    }

    /**
     * 馆藏书目查询
     * POST /api/v1.0/book-manage-api/book-item/query
     *
     * @param bookItemQueryDTO 查询条件类
     * @return Result<List < BookItemVO>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/query")
    public Result<List<BookItemVO>> queryPage(@RequestBody BookItemQueryDTO bookItemQueryDTO) {
        List<BookItemVO> bookItemVOS = bookItemService.queryPage(bookItemQueryDTO);
        Integer count = bookItemService.queryPageCount(bookItemQueryDTO);
        return ApiResult.success(bookItemVOS, count);
    }

}
