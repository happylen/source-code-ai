package com.kmbeast.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 馆藏书目实体
 */
@TableName(value = "book_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookItem {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 条形码
     */
    private String code;
    /**
     * 书目ID，外键，关联的是书目信息表
     */
    private Integer booklistId;
    /**
     * 馆藏位置
     */
    private String location;
    /**
     * 备注
     */
    private String record;
    /**
     * 购买时价格
     */
    private BigDecimal buyPrice;
    /**
     * 购买日期
     */
    private LocalDate buyDate;
    /**
     * 状态（1-在馆；2-借出；3-损毁；4-销毁）
     */
    private Integer status;
}
