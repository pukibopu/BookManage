package com.book.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Borrow {
    int bookId;
    String bookName;
    Date time;
    String studentName;
    int StudentId;

}
