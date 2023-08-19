package com.zjj.webserver;


import com.zjj.Beans.User;
import com.zjj.Mapper.MapperFile;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/register")
public class registerserver extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get-ok");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理中文乱码
        resp.setHeader("content-type","text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String source = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        MapperFile mapperFile = sqlSession.getMapper(MapperFile.class);
        int count = mapperFile.SelectCountByUsername(username);
        PrintWriter printWriter = resp.getWriter();
        if(count!=0){
            printWriter.write("用户名重复");
        }else{
            User user = new User(username,password);
            mapperFile.add(user);
            printWriter.write(String.format("<h1>Success,Your ID is %d</h1>",user.getId()));
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
