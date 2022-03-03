package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.StudentMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.service.BookService;
import com.book.utlis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrowInfo() {
        try(SqlSession session= MybatisUtil.getSession(true)){
            BookMapper mapper=session.getMapper(BookMapper.class);
            return mapper.getBorrowInfo();
        }
    }

    @Override
    public List<Book> getBookActiveInfo() {
        Set<Integer> set=new HashSet<>();
        this.getBorrowInfo().forEach(borrow -> set.add(borrow.getBookId()));

        try(SqlSession session=MybatisUtil.getSession(true)){
            BookMapper mapper=session.getMapper(BookMapper.class);
           return mapper.getBookList().stream().
                   filter(book -> !set.contains(book.getBid())).collect(Collectors.toList());
        }
    }

    @Override
    public void returnBook(String id) {
        try(SqlSession session=MybatisUtil.getSession(true)){
            BookMapper mapper=session.getMapper(BookMapper.class);
            mapper.deleteBorrow(id);
        }
    }

    @Override
    public List<Student> getStudentInfo() {
        try(SqlSession session=MybatisUtil.getSession(true)){
            StudentMapper mapper=session.getMapper(StudentMapper.class);
           return mapper.getStudentList();
        }
    }
}
