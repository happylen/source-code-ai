package com.kmbeast.pojo.vo;

import com.kmbeast.pojo.entity.Booklist;
import com.kmbeast.pojo.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 书目VO类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooklistVO extends Booklist {

    /**
     * 书目关联的图书类别集合
     */
    private List<Category> categoryList;

}
