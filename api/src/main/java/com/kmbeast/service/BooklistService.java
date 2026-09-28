package com.kmbeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kmbeast.pojo.dto.BooklistQueryDTO;
import com.kmbeast.pojo.dto.BooklistSaveDTO;
import com.kmbeast.pojo.entity.Booklist;
import com.kmbeast.pojo.vo.BooklistDetailVO;
import com.kmbeast.pojo.vo.BooklistLendTopVO;
import com.kmbeast.pojo.vo.BooklistVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 书目业务逻辑接口
 */
public interface BooklistService extends IService<Booklist> {

    void addBooklist(BooklistSaveDTO booklistSaveDTO);

    void updateBooklist(BooklistSaveDTO booklistSaveDTO);

    void delBooklist(Integer id);

    List<BooklistVO> queryPage(BooklistQueryDTO booklistQueryDTO);

    Integer queryPageCount(BooklistQueryDTO booklistQueryDTO);

    BooklistDetailVO detail(Integer id);

    List<BooklistVO> collection(BooklistQueryDTO booklistQueryDTO);

    List<LocalDateTime> launchInfo();

    List<BooklistVO> lendTop(Integer count);

    List<BooklistLendTopVO> booklistLendTopVOS(Integer count);

    List<BooklistVO> recommend(Integer count);

}
