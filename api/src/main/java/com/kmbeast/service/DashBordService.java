package com.kmbeast.service;

import com.kmbeast.pojo.vo.ChartsVO;

import java.util.List;

/**
 * 仪表盘业务逻辑接口
 */
public interface DashBordService {

    List<ChartsVO> types();

    List<ChartsVO> staticValueCount();

    List<ChartsVO> booklistLaunchInfo();

    List<ChartsVO> borrowImpact();

}
