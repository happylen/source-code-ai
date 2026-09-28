package com.kmbeast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kmbeast.pojo.dto.LoansQueryDTO;
import com.kmbeast.pojo.entity.Loans;
import com.kmbeast.pojo.vo.BooklistLendTopVO;
import com.kmbeast.pojo.vo.LoansVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 借阅记录业务逻辑接口
 */
public interface LoansService extends IService<Loans> {

    void addLoans(Loans loans);

    void updateLoans(Loans loans);

    void delCategory(Integer id);

    List<LoansVO> queryPage(LoansQueryDTO loansQueryDTO);

    Integer queryPageCount(LoansQueryDTO loansQueryDTO);

    void returnBook(Integer id);

    Integer lendOrOverdueCount();

    List<BooklistLendTopVO> queryBooklistLendTop(Integer count);

}
