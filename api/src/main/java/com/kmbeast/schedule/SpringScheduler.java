package com.kmbeast.schedule;

import com.kmbeast.pojo.dto.LoansQueryDTO;
import com.kmbeast.pojo.entity.Loans;
import com.kmbeast.pojo.enums.BookLoansStatusEnum;
import com.kmbeast.pojo.vo.LoansVO;
import com.kmbeast.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 逾期定时任务守护
 */
@Component
@EnableScheduling
public class SpringScheduler {

    @Autowired
    private LoansService loansService;

    // 每60秒执行一次
    @Scheduled(fixedRate = 60000)
    public void fixedRateTask() {
        System.out.println("逾期周期任务执行中...");
        List<LoansVO> loansVOS = loansService.queryPage(new LoansQueryDTO());
        List<Loans> loansList = new ArrayList<>();
        for (LoansVO loansVO : loansVOS) {
            // 计划归还日期
            LocalDate planReturnDate = loansVO.getPlanReturnDate();
            // 实际归还日期
            LocalDateTime realReturnDate = loansVO.getRealReturnDate();

            if (realReturnDate == null && planReturnDate.isBefore(LocalDate.now())) {
                Loans loans = new Loans();
                loans.setId(loansVO.getId());
                loans.setStatus(BookLoansStatusEnum.STATUS_2.getStatus());
                loansList.add(loans);
            }
        }
        if (!CollectionUtils.isEmpty(loansList)) {
            System.out.println("实际逾期数据："+loansList);
            loansService.updateBatchById(
                    loansList
            );
        }
    }
}