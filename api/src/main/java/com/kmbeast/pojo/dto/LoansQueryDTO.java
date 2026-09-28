package com.kmbeast.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 借阅记录查询条件类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansQueryDTO extends QueryDTO {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 馆藏书目ID
     */
    private Integer bookItemId;

    /**
     * 状态（1-借阅中；2-已逾期；3-已归还）
     */
    private Integer status;

    /**
     * 状态列表
     */
    private List<Integer> statusList;

    /**
     * 借阅时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
