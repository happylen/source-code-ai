package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.NoticeQueryDTO;
import com.kmbeast.pojo.entity.Notice;
import com.kmbeast.pojo.vo.ChartsVO;
import com.kmbeast.pojo.vo.NoticeListVO;
import com.kmbeast.service.DashBordService;
import com.kmbeast.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仪表盘控制器
 */
@RestController
@RequestMapping("/dashbord")
public class DashBordController {

    @Autowired
    private DashBordService dashBordService;

    /**
     * 查询类别下的图书数量 - 饼状图
     * GET /api/v1.0/book-manage-api/dashbord/types
     *
     * @return Result<List<ChartsVO>> 后台通用返回封装类
     */
    @GetMapping(value = "/types")
    public Result<List<ChartsVO>> types() {
        List<ChartsVO> pieChartsVOS =  dashBordService.types();
        return ApiResult.success(pieChartsVOS);
    }

    /**
     * 静态数据统计
     * GET /api/v1.0/book-manage-api/dashbord/staticValueCount
     *
     * @return Result<List<ChartsVO>> 后台通用返回封装类
     */
    @GetMapping(value = "/staticValueCount")
    public Result<List<ChartsVO>> staticValueCount() {
        List<ChartsVO> pieChartsVOS =  dashBordService.staticValueCount();
        return ApiResult.success(pieChartsVOS);
    }

    /**
     * 静态数据统计
     * GET /api/v1.0/book-manage-api/dashbord/booklistLaunchInfo
     *
     * @return Result<List<ChartsVO>> 后台通用返回封装类
     */
    @GetMapping(value = "/booklistLaunchInfo")
    public Result<List<ChartsVO>> booklistLaunchInfo() {
        List<ChartsVO> pieChartsVOS =  dashBordService.booklistLaunchInfo();
        return ApiResult.success(pieChartsVOS);
    }

    /**
     * 借阅影响馆藏指数
     * GET /api/v1.0/book-manage-api/dashbord/borrowImpact
     *
     * @return Result<List<ChartsVO>> 借出数量、馆藏总量、可用馆藏、借阅影响率
     */
    @GetMapping(value = "/borrowImpact")
    public Result<List<ChartsVO>> borrowImpact() {
        List<ChartsVO> pieChartsVOS = dashBordService.borrowImpact();
        return ApiResult.success(pieChartsVOS);
    }

}
