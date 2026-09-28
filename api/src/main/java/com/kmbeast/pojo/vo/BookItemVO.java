package com.kmbeast.pojo.vo;

import com.kmbeast.pojo.entity.BookItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 馆藏书目VO类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookItemVO extends BookItem {

    /**
     * 书目封面
     */
    private String bookCover;
    /**
     * 书目名称
     */
    private String bookName;
    /**
     * 书目作者
     */
    private String bookAuthor;
}
