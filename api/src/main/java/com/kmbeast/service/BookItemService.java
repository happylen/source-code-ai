package com.kmbeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kmbeast.pojo.dto.BookItemQueryDTO;
import com.kmbeast.pojo.entity.BookItem;
import com.kmbeast.pojo.vo.BookItemVO;

import java.util.List;

/**
 * 馆藏书目业务逻辑接口
 */
public interface BookItemService extends IService<BookItem> {

    void addBookItem(BookItem bookItem);

    void updateBookItem(BookItem bookItem);

    void delBookItem(Integer id);

    List<BookItemVO> queryPage(BookItemQueryDTO bookItemQueryDTO);

    Integer queryPageCount(BookItemQueryDTO bookItemQueryDTO);

}
