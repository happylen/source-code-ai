package com.kmbeast.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 图书借阅记录实体
 */
@TableName(value = "loans")
@Data
public class Loans {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 馆藏书目ID
     */
    private Integer bookItemId;

    /**
     * 状态（1-借阅中；2-已逾期；3-已归还）
     */
    private Integer status;

    /**
     * 借出日期
     */
    private LocalDate lendDate;

    /**
     * 计划归还日期
     */
    private LocalDate planReturnDate;

    /**
     * 实际归还时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 HH:mm")
    private LocalDateTime realReturnDate;

    /**
     * 借阅时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
