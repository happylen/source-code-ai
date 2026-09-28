package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.mapper.BookItemMapper;
import com.kmbeast.pojo.dto.BookItemQueryDTO;
import com.kmbeast.pojo.entity.BookItem;
import com.kmbeast.pojo.enums.BookItemStatusEnum;
import com.kmbeast.pojo.vo.BookItemVO;
import com.kmbeast.service.BookItemService;
import com.kmbeast.utils.AssertUtils;
import com.kmbeast.utils.RoleValidUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 馆藏书目业务逻辑实现接口
 */
@Service
public class BookItemServiceImpl extends ServiceImpl<BookItemMapper, BookItem> implements BookItemService {

    /**
     * 参数校验
     *
     * @param bookItem 书目实体类
     */
    private void validParams(BookItem bookItem) {
        AssertUtils.notNull(bookItem, "参数项不为空");
        AssertUtils.hasText(bookItem.getLocation(), "馆藏位置不为空");
        AssertUtils.notNull(bookItem.getBooklistId(), "书目ID不为空");
    }

    /**
     * 馆藏书目新增
     *
     * @param bookItem 馆藏书目
     */
    @Override
    public void addBookItem(BookItem bookItem) {
        validParams(bookItem);
        // 新增时，馆藏书目的状态是 - 在馆
        bookItem.setStatus(BookItemStatusEnum.STATUS_1.getStatus());
        // 设置条形码
        bookItem.setCode(createCode());
        RoleValidUtils.requestedAdmin("无操作权限");
        save(bookItem);
    }

    /**
     * 使用UUID作为条形码
     *
     * @return String
     */
    private String createCode() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 馆藏书目修改
     *
     * @param bookItem 馆藏书目
     */
    @Override
    public void updateBookItem(BookItem bookItem) {
        validParams(bookItem);
        AssertUtils.notNull(bookItem.getId(), "ID不为空");
        RoleValidUtils.requestedAdmin("无操作权限");
        updateById(bookItem);
    }

    /**
     * 馆藏书目删除
     *
     * @param id 主键ID
     */
    @Override
    public void delBookItem(Integer id) {
        AssertUtils.notNull(id, "ID不为空");
        RoleValidUtils.requestedAdmin("无操作权限");
        removeById(id);
    }

    /**
     * 查询馆藏书目
     *
     * @param bookItemQueryDTO 查询条件类
     * @return List<BookItemVO>
     */
    @Override
    public List<BookItemVO> queryPage(BookItemQueryDTO bookItemQueryDTO) {
        return this.baseMapper.queryPage(bookItemQueryDTO);
    }

    /**
     * 查询馆藏书目总条数
     *
     * @param bookItemQueryDTO 查询条件类
     * @return Integer
     */
    @Override
    public Integer queryPageCount(BookItemQueryDTO bookItemQueryDTO) {
        return this.baseMapper.queryPageCount(bookItemQueryDTO);
    }

}
