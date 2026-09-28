package com.kmbeast.service.impl;

import com.kmbeast.mapper.DashBordMapper;
import com.kmbeast.pojo.vo.ChartsVO;
import com.kmbeast.service.BooklistService;
import com.kmbeast.service.CategoryService;
import com.kmbeast.service.DashBordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 仪表盘业务逻辑接口实现类
 */
@Service
public class DashBordServiceImpl implements DashBordService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DashBordMapper dashBordMapper;
    @Autowired
    private BooklistService booklistService;

    /**
     * 统计类别下的图书数量
     *
     * @return List<ChartsVO>
     */
    @Override
    public List<ChartsVO> types() {
        return categoryService.types();
    }

    /**
     * 静态数据统计
     *
     * @return List<ChartsVO>
     */
    @Override
    public List<ChartsVO> staticValueCount() {
        return dashBordMapper.staticValueCount();
    }

    /**
     * 统计图书上架情况
     *
     * @return List<ChartsVO>
     */
    /**
     * 借阅影响馆藏指数
     *
     * @return List<ChartsVO>
     */
    @Override
    public List<ChartsVO> borrowImpact() {
        return dashBordMapper.borrowImpact();
    }

    @Override
    public List<ChartsVO> booklistLaunchInfo() {
        List<LocalDateTime> localDateTimes = booklistService.launchInfo();
        if (CollectionUtils.isEmpty(localDateTimes)) {
            return new ArrayList<>();
        }
        return localDateTimes.stream()
                .collect(Collectors.groupingBy(
                        // 直接使用 toLocalDate() 的 toString() 方法，默认格式就是 yyyy-MM-dd
                        dateTime -> dateTime.toLocalDate().toString(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(entry -> new ChartsVO(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }
}
