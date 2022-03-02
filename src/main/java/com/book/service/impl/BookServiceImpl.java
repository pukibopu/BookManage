package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.entity.Borrow;
import com.book.service.BookService;
import com.book.utlis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrowInfo() {
        try(SqlSession session= MybatisUtil.getSession(true)){
            BookMapper mapper=session.getMapper(BookMapper.class);
            return mapper.getBorrowInfo();
        }
    }

    @Override
    public void returnBook(String id) {
        try(SqlSession session=MybatisUtil.getSession(true)){
            BookMapper mapper=session.getMapper(BookMapper.class);
            mapper.deleteBorrow(id);
        }
    }
}
