package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.mapper.LoansMapper;
import com.kmbeast.pojo.dto.LoansQueryDTO;
import com.kmbeast.pojo.entity.BookItem;
import com.kmbeast.pojo.entity.Loans;
import com.kmbeast.pojo.enums.BookItemStatusEnum;
import com.kmbeast.pojo.enums.BookLoansStatusEnum;
import com.kmbeast.pojo.vo.BooklistLendTopVO;
import com.kmbeast.pojo.vo.LoansVO;
import com.kmbeast.service.BookItemService;
import com.kmbeast.service.LoansService;
import com.kmbeast.utils.AssertUtils;
import com.kmbeast.utils.RoleValidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 借阅记录业务逻辑实现类
 */
@Service
public class LoansServiceImpl extends ServiceImpl<LoansMapper, Loans> implements LoansService {

    @Autowired
    private BookItemService bookItemService;

    /**
     * 参数校验
     *
     * @param loans 图书类别实体类
     */
    private void validParams(Loans loans) {
        AssertUtils.notNull(loans, "参数项不为空");
        AssertUtils.notNull(loans.getBookItemId(), "馆藏书目不为空");
        AssertUtils.notNull(loans.getPlanReturnDate(), "计划归还时间请补充");
    }


    /**
     * 借阅记录新增
     *
     * @param loans loans
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addLoans(Loans loans) {
        // 参数校验
        validParams(loans);
        // 默认值设置
        setDefaultProperty(loans);
        // 归还时间检验
        validReturnData(loans);
        // 校验书籍状态
        validBookItemStatus(loans);
        // 限制个人借阅的次数
        validPersonalLoanCoun();
        // 修改馆藏书目的状态
        updateBookItemStatus(
                loans.getBookItemId(),
                BookItemStatusEnum.STATUS_2.getStatus()
        );
        save(loans);
    }

    /**
     * 修改馆藏书目的状态
     *
     * @param id     馆藏书籍ID
     * @param status 馆藏书籍状态
     */
    private void updateBookItemStatus(Integer id, Integer status) {
        BookItem bookItem = new BookItem();
        bookItem.setId(id);
        bookItem.setStatus(status);
        bookItemService.updateById(bookItem);
    }

    /**
     * 限制个人借阅的次数
     * - 比一个人当前借阅了3本书，都还没有归还，不能再继续借阅
     * - 只有当正在借阅的书籍数量在3本以下，可以正常借阅
     */
    private void validPersonalLoanCoun() {
        long count = count(
                new LambdaQueryWrapper<Loans>()
                        .eq(Loans::getUserId, LocalThreadHolder.getUserId())
                        .eq(Loans::getStatus, BookLoansStatusEnum.STATUS_1.getStatus())
        );
        AssertUtils.isTrue(count <= 3, "您当前借阅了多本书，仍未归还，请归还后再借");
    }

    /**
     * 检验书籍状态
     * 只有书籍处在 - 在馆 - 状态的时候，才能借
     *
     * @param loans 借阅记录
     */
    private void validBookItemStatus(Loans loans) {
        BookItem bookItem = bookItemService.getById(loans.getBookItemId());
        AssertUtils.notNull(bookItem, "馆藏书籍查询异常");
        AssertUtils.isTrue(BookItemStatusEnum.STATUS_1.getStatus().equals(bookItem.getStatus()), "书籍状态异常，不可借");
    }

    /**
     * 新增时，设置的计划归还时间，不可于实际借出日期之前
     *
     * @param loans 借阅记录实体
     */
    private void validReturnData(Loans loans) {
        AssertUtils.isTrue(loans.getPlanReturnDate().isAfter(loans.getLendDate()), "计划归还时间不能在当前时间前");
    }

    /**
     * 设置初始值
     *
     * @param loans 借阅记录实体
     */
    private void setDefaultProperty(Loans loans) {
        // 借阅时间
        loans.setCreateTime(LocalDateTime.now());
        // 当前借书的用户ID - 通过本地线程获取
        loans.setUserId(LocalThreadHolder.getUserId());
        // 设置借阅的状态 - 借阅中
        loans.setStatus(BookLoansStatusEnum.STATUS_1.getStatus());
        // 设置借出日期 - 以当前日期为准
        loans.setLendDate(LocalDate.now());
    }


    @Override
    public void updateLoans(Loans loans) {

    }

    /**
     * 删除借阅记录
     *
     * @param id 主键ID
     */
    @Override
    public void delCategory(Integer id) {
        RoleValidUtils.requestedAdmin("无操作权限");
        removeById(id);
    }

    @Override
    public List<LoansVO> queryPage(LoansQueryDTO loansQueryDTO) {
        return this.baseMapper.queryPage(loansQueryDTO);
    }

    @Override
    public Integer queryPageCount(LoansQueryDTO loansQueryDTO) {
        return this.baseMapper.queryPageCount(loansQueryDTO);
    }

    /**
     * 还书
     *
     * @param id 借阅记录主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnBook(Integer id) {
        AssertUtils.notNull(id, "ID不为空");
        // 还书
        Loans loans = getById(id);
        AssertUtils.notNull(loans, "借阅记录查询异常");
        // 校验还的人跟借的人是否一致
        AssertUtils.equals(
                loans.getUserId(),
                LocalThreadHolder.getUserId(),
                "非法请求"
        );
        Loans loansUpdateEntity = new Loans();
        loansUpdateEntity.setId(id);
        loansUpdateEntity.setStatus(BookLoansStatusEnum.STATUS_3.getStatus());
        loansUpdateEntity.setRealReturnDate(LocalDateTime.now());
        updateById(loansUpdateEntity);
        // 恢复馆藏书籍状态
        BookItem bookItem = bookItemService.getById(loans.getBookItemId());
        AssertUtils.notNull(bookItem, "馆藏书目查询异常");
        BookItem bookItemUpdateEntity = new BookItem();
        bookItemUpdateEntity.setId(bookItem.getId());
        bookItemUpdateEntity.setStatus(BookItemStatusEnum.STATUS_1.getStatus());
        bookItemService.updateById(bookItemUpdateEntity);
    }


    @Override
    public Integer lendOrOverdueCount() {
        List<Integer> statusList = List.of(
                BookLoansStatusEnum.STATUS_1.getStatus(),
                BookLoansStatusEnum.STATUS_2.getStatus()
        );
        LoansQueryDTO loansQueryDTO = new LoansQueryDTO();
        loansQueryDTO.setUserId(LocalThreadHolder.getUserId());
        loansQueryDTO.setStatusList(statusList);
        return this.baseMapper.queryPageCount(loansQueryDTO);
    }

    @Override
    public List<BooklistLendTopVO> queryBooklistLendTop(Integer count) {
        return this.baseMapper.queryBooklistLendTop(count);
    }
}
