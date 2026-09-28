package com.kmbeast.pojo.dto;

import com.kmbeast.pojo.entity.Booklist;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 书目新增DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BooklistSaveDTO extends Booklist {
    /**
     * 图书类别ID集合
     */
    private List<Integer> categoryIds;
}
