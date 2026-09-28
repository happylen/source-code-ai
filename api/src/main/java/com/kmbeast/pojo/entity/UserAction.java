package com.kmbeast.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户行为操作实体
 */
@TableName(value = "user_action")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAction {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID，外键，关联的是用户表
     */
    private Integer userId;

    /**
     * 书目ID，外键，关联的是书目信息表
     */
    private Integer booklistId;

    /** 
     * 类型（1-收藏；2-点击；3-想看；4-停留时长）
     */
    private Integer type;

    /**
     * 停留时间（只有当行为操作类型是'停留'是才需要设置）
     */
    private Long stayTime;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
