package com.kmbeast.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 公告列表VO类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListVO {

    /**
     * ID
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日")
    private LocalDateTime createTime;

}
