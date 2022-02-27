package com.book.service.impl;

import com.book.entity.User;
import com.book.dao.UserMapper;
import com.book.service.UserService;
import com.book.utlis.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class UserImpl implements UserService {
    @Override
    public boolean auth(String username, String password, HttpSession httpSession) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);
            if (user == null) return false;
            httpSession.setAttribute("user",user);
            return true;
        }
    }
}
