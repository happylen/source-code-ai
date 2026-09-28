package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.entity.BookCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 书目与图书类别关联持久化接口
 */
@Mapper
public interface BookCategoryMapper extends BaseMapper<BookCategory> {

}
