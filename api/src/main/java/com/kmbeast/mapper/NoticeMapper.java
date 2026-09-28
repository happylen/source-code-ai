package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.CategoryQueryDTO;
import com.kmbeast.pojo.dto.NoticeQueryDTO;
import com.kmbeast.pojo.entity.Category;
import com.kmbeast.pojo.entity.Notice;
import com.kmbeast.pojo.vo.CategoryVO;
import com.kmbeast.pojo.vo.NoticeListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公告持久化接口
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    List<NoticeListVO> queryPage(NoticeQueryDTO noticeQueryDTO);

    Integer queryPageCount(NoticeQueryDTO noticeQueryDTO);

}
