package com.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 书目详情VO类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooklistDetailVO extends BooklistVO {
    /**
     * 书目关联的馆藏本信息
     */
    private List<BookItemVO> bookItemVOS;
}
