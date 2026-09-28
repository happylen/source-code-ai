package com.kmbeast.pojo.vo;

import com.kmbeast.pojo.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图书类别VO类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryVO extends Category {

    /**
     * 其下是否有子类别标识
     */
    private Boolean hasChildren;

}
