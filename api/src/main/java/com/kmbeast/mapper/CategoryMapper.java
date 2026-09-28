package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.CategoryQueryDTO;
import com.kmbeast.pojo.entity.Category;
import com.kmbeast.pojo.vo.CategoryVO;
import com.kmbeast.pojo.vo.ChartsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 图书类别持久化接口
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVO> queryPage(CategoryQueryDTO categoryQueryDTO);

    Integer queryPageCount(CategoryQueryDTO categoryQueryDTO);

    List<ChartsVO> types();

}
