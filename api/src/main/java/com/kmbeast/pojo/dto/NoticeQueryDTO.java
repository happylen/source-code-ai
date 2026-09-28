package com.kmbeast.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 公告查询条件类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeQueryDTO extends QueryDTO {
    /**
     * 标题
     */
    private String title;
}
