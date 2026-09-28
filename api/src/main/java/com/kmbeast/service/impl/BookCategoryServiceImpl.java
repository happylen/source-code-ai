package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.mapper.BookCategoryMapper;
import com.kmbeast.pojo.entity.BookCategory;
import com.kmbeast.service.BookCategoryService;
import org.springframework.stereotype.Service;

/**
 * 书目与图书类别关联业务逻辑接口实现类
 */
@Service
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

}
