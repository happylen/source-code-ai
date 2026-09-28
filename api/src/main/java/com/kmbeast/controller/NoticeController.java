package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.NoticeQueryDTO;
import com.kmbeast.pojo.entity.Notice;
import com.kmbeast.pojo.vo.NoticeListVO;
import com.kmbeast.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告控制器
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 公告新增
     * POST /api/v1.0/book-manage-api/notice
     *
     * @param notice 公告实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping
    public Result<Void> addNotice(@RequestBody Notice notice) {
        noticeService.addNotice(notice);
        return ApiResult.success();
    }

    /**
     * 公告修改
     * POST /api/v1.0/book-manage-api/notice/update
     *
     * @param notice 公告实体
     * @return Result<Void> 后台通用返回封装类
     */
    @PostMapping("/update")
    public Result<Void> updateNotice(@RequestBody Notice notice) {
        noticeService.updateNotice(notice);
        return ApiResult.success();
    }


    /**
     * 查询公告详情
     * GET /api/v1.0/book-manage-api/notice/{id}/detail
     *
     * @param id 公告主键ID
     * @return Result<Notice> 后台通用返回封装类
     */
    @GetMapping("/{id}/detail")
    public Result<Notice> detail(@PathVariable Integer id) {
        Notice notice = noticeService.detail(id);
        return ApiResult.success(notice);
    }

    /**
     * 公告删除
     * DELETE /api/v1.0/book-manage-api/notice/{id}
     *
     * @param id 公告主键ID
     * @return Result<Void> 后台通用返回封装类
     */
    @DeleteMapping("/{id}")
    public Result<Void> delNotice(@PathVariable Integer id) {
        noticeService.delNotice(id);
        return ApiResult.success();
    }

    /**
     * 公告查询
     * POST /api/v1.0/book-manage-api/notice/query
     *
     * @param noticeQueryDTO 查询条件类
     * @return Result<List < NoticeListVO>> 后台通用返回封装类
     */
    @Pager
    @PostMapping("/query")
    public Result<List<NoticeListVO>> queryPage(@RequestBody NoticeQueryDTO noticeQueryDTO) {
        List<NoticeListVO> noticeListVOS = noticeService.queryPage(noticeQueryDTO);
        Integer count = noticeService.queryPageCount(noticeQueryDTO);
        return ApiResult.success(noticeListVOS, count);
    }

}
