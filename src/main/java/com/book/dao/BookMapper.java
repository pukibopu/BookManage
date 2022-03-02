package com.book.dao;

import com.book.entity.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "bid",property = "bookId"),
            @Result(column = "title",property = "bookName"),
            @Result(column = "date",property = "time"),
            @Result(column = "name",property = "studentName"),
            @Result(column = "sid",property = "studentId")

    })
    @Select("select *from borrow,student , book where borrow.sid = student.sid and borrow.bid = book.bid")
    List<Borrow> getBorrowInfo();
    @Delete("delete from borrow where id=#{id}")
    void deleteBorrow(String id);
}
