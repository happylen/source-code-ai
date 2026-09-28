package com.kmbeast.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 书目查询条件类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BooklistQueryDTO extends QueryDTO{

    /**
     * 名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版商
     */
    private String publisher;

    /**
     * 书目ID列表
     */
    private List<Integer> booklistIds;

    /**
     * 图书类别ID
     */
    private List<Integer> categoryIds;

}
