package com.kmbeast.pojo.vo;

import com.kmbeast.pojo.entity.Loans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 借阅记录VO类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansVO extends Loans {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 馆藏书目条形码
     */
    private String code;
    /**
     * 馆藏书目状态
     */
    private Integer bookItemStatus;
    /**
     * 书目封面
     */
    private String cover;
    /**
     * 书目ID
     */
    private Integer booklistId;
    /**
     * 书目名
     */
    private String booklistName;
    /**
     * 书目作者
     */
    private String booklistAuthor;
    /**
     * 书目介绍
     */
    private String booklistDetail;
    /**
     * 出版社
     */
    private String booklistPublisher;
}
