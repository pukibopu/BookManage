package com.book.servlet.manage;

import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utlis.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
@WebServlet("/add-borrow")
public class AddBorrow extends HttpServlet {
    BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService=new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        context.setVariable("book_list",bookService.getBookActiveInfo());
        context.setVariable("student_list",bookService.getStudentInfo());
        ThymeleafUtil.process("add-borrow.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sid=Integer.parseInt(req.getParameter("student"));
        Integer bid=Integer.parseInt(req.getParameter("book"));
        bookService.addBorrow(sid,bid);
        resp.sendRedirect("index");
    }
}
