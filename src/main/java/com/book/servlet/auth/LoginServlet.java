package com.book.servlet.auth;

import com.book.service.UserService;
import com.book.service.impl.UserImpl;
import com.book.utlis.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if (req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", true);
            req.getSession().removeAttribute("login-failure");
        }
        if (req.getSession().getAttribute("user") != null){
            resp.sendRedirect("index");
            return;
        }
            ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember-me");
        if (service.auth(username, password, req.getSession())) {
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", true);
            this.doGet(req, resp);
        }


    }
}
