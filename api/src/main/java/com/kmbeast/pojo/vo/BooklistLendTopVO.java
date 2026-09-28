package com.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 借出图书VO类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooklistLendTopVO {
    private Integer booklistId;
    private String booklistName;
    private Integer totalBorrowCount;
}
