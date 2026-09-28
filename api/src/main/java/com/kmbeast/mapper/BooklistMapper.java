package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.BooklistQueryDTO;
import com.kmbeast.pojo.entity.Booklist;
import com.kmbeast.pojo.vo.BooklistDetailVO;
import com.kmbeast.pojo.vo.BooklistVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 书目持久化接口
 */
@Mapper
public interface BooklistMapper extends BaseMapper<Booklist> {

    List<BooklistVO> queryPage(BooklistQueryDTO booklistQueryDTO);

    Integer queryPageCount(BooklistQueryDTO booklistQueryDTO);

    BooklistDetailVO detail(@Param(value = "id") Integer id);

    List<LocalDateTime> launchInfo();


    List<Integer> getIds();

}
