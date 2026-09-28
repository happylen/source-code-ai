package com.kmbeast.mapper;

import com.kmbeast.pojo.vo.ChartsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 仪表盘持久化接口
 */
@Mapper
public interface DashBordMapper {

    List<ChartsVO> staticValueCount();

    List<ChartsVO> borrowImpact();

}
