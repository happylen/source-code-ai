package com.kmbeast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kmbeast.pojo.dto.LoansQueryDTO;
import com.kmbeast.pojo.entity.Loans;
import com.kmbeast.pojo.vo.BooklistLendTopVO;
import com.kmbeast.pojo.vo.LoansVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 借阅记录持久化接口
 */
@Mapper
public interface LoansMapper extends BaseMapper<Loans> {

    List<LoansVO> queryPage(LoansQueryDTO loansQueryDTO);

    Integer queryPageCount(LoansQueryDTO loansQueryDTO);

    /**
     * 查询借阅最多的图书
     *
     * @param count 本数
     * @return List<Integer>
     */
    List<BooklistLendTopVO> queryBooklistLendTop(@Param(value = "count") Integer count);

}
