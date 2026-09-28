package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.mapper.NoticeMapper;
import com.kmbeast.pojo.dto.NoticeQueryDTO;
import com.kmbeast.pojo.entity.Notice;
import com.kmbeast.pojo.vo.NoticeListVO;
import com.kmbeast.service.NoticeService;
import com.kmbeast.utils.AssertUtils;
import com.kmbeast.utils.RoleValidUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告业务逻辑实现类
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


    /**
     * 参数校验
     *
     * @param notice 图书类别实体类
     */
    private void validParams(Notice notice) {
        AssertUtils.notNull(notice, "参数项不为空");
        AssertUtils.hasText(notice.getTitle(), "标题不为空");
    }

    /**
     * 公告新增
     *
     * @param notice 公告数据
     */
    @Override
    public void addNotice(Notice notice) {
        RoleValidUtils.requestedAdmin("无操作权限");
        validParams(notice);
        notice.setCreateTime(LocalDateTime.now());
        save(notice);
    }

    /**
     * 公告新增
     *
     * @param notice 公告数据
     */
    @Override
    public void updateNotice(Notice notice) {
        RoleValidUtils.requestedAdmin("无操作权限");
        validParams(notice);
        updateById(notice);
    }

    /**
     * 公告删除
     *
     * @param id 主键ID
     */
    @Override
    public void delNotice(Integer id) {
        RoleValidUtils.requestedAdmin("无操作权限");
        removeById(id);
    }


    @Override
    public List<NoticeListVO> queryPage(NoticeQueryDTO noticeQueryDTO) {
        return this.baseMapper.queryPage(noticeQueryDTO);
    }

    @Override
    public Integer queryPageCount(NoticeQueryDTO noticeQueryDTO) {
        return this.baseMapper.queryPageCount(noticeQueryDTO);
    }

    @Override
    public Notice detail(Integer id) {
        AssertUtils.notNull(id,"ID不为空");
        return getById(id);
    }
}
