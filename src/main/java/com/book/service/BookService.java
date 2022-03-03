package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;

import java.util.List;

public interface BookService {
    List<Borrow> getBorrowInfo();
    void returnBook(String id);
    List<Book> getBookActiveInfo();
    List<Student> getStudentInfo();
}
