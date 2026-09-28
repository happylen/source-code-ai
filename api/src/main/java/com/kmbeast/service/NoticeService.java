package com.kmbeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kmbeast.pojo.dto.NoticeQueryDTO;
import com.kmbeast.pojo.entity.Notice;
import com.kmbeast.pojo.vo.NoticeListVO;

import java.util.List;

/**
 * 公告业务逻辑接口
 */
public interface NoticeService extends IService<Notice> {

    void addNotice(Notice notice);

    void updateNotice(Notice notice);

    void delNotice(Integer id);

    List<NoticeListVO> queryPage(NoticeQueryDTO noticeQueryDTO);

    Integer queryPageCount(NoticeQueryDTO noticeQueryDTO);

    Notice detail(Integer id);

}
