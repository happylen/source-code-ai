package com.kmbeast.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 书目实体
 */
@TableName(value = "booklist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booklist {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 书目名称
     */
    private String name;

    /**
     * ISBN（国际标准书号）
     */
    private String isbn;

    /**
     * 简介
     */
    private String detail;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版商
     */
    private String publisher;

    /**
     * 出版年份
     */
    private String publishYear;

    /**
     * 版次
     */
    private Integer publishCount;

    /**
     * 封面
     */
    private String cover;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
