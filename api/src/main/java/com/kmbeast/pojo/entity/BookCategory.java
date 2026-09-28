package com.kmbeast.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//Mybatis Plus + Mybatis（混合开发）

/**
 * 书籍类别关联表实体
 */
@TableName(value = "book_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategory {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 书目ID，外键，关联的是书目信息表
     */
    private Integer booklistId;
    /**
     * 图书类别ID，外键。关联的是图书类别表
     */
    private Integer categoryId;
}
