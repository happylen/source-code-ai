package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.BookItemQueryDTO;
import com.kmbeast.pojo.entity.BookItem;
import com.kmbeast.pojo.vo.BookItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 馆藏书目持久化接口
 */
@Mapper
public interface BookItemMapper extends BaseMapper<BookItem> {

    List<BookItemVO> queryPage(BookItemQueryDTO bookItemQueryDTO);

    Integer queryPageCount(BookItemQueryDTO bookItemQueryDTO);

}
