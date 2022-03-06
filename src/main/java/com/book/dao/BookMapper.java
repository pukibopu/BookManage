package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

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

    @Select("select *from book")
    List<Book> getBookList();

    @Insert("insert into borrow(sid,bid,date) values(#{sid},#{bid},Now())")
    void addBorrow(@Param("sid") int sid,@Param("bid") int bid);
}
