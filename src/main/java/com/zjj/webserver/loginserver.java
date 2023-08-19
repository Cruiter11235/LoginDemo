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

@WebServlet("/loginserver")
public class loginserver extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type","text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String source = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(source);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        MapperFile mapperFile = sqlSession.getMapper(MapperFile.class);
        PrintWriter printWriter = resp.getWriter();
        int count = mapperFile.SelectCountByUsername(username);
        if(count!=0){
            User user = mapperFile.SeletByUserName(username);
            if(password.equals(user.getPassword())){
                printWriter.write("登录成功");
            }else{
                printWriter.write("登陆失败");
            }
        }else{
            printWriter.write("用户名不存在");
        }

        sqlSession.close();
    }
}
