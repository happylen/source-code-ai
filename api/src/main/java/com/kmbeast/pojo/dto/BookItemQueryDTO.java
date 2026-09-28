package com.kmbeast.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 馆藏书目查询条件类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookItemQueryDTO extends QueryDTO {

    /**
     * 条形码
     */
    private String code;
    /**
     * 书目ID，外键，关联的是书目信息表
     */
    private Integer booklistId;
    /**
     * 状态（1-在馆；2-借出；3-损毁；4-销毁）
     */
    private Integer status;

}
