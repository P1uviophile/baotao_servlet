package com.system.servlet;

import com.system.bean.User;
import com.system.service.IUserService;
import com.system.service.impl.UserServiceImpl;
import com.system.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;


/**
 * 处理登录和注销的Servlet
 */
@WebServlet(name = "loginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    IUserService userService = new UserServiceImpl();
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //实现登录的功能
        //1.获取表单的账号密码
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        LOGGER.info("Received userName: " + userName);
        LOGGER.info("Received password: " + password);
        //2.调用Service的相关方法来实现处理
        User user = userService.checkUserNameAndPassword(userName, password);
        System.out.println(user);
        session = req.getSession();
        //3.根据验证的结果来做出对应的相应
        if(user != null){
            //登录成功
            LOGGER.info("Login successful");
            user.setPassword(null);
            session.setAttribute(Constant.SESSION_LOGIN_USER,user);
            if (user.getAdmin()==1) session.setAttribute("admin",true);
            else session.setAttribute("admin",false);
            System.out.println(session.getAttribute(Constant.SESSION_LOGIN_USER));
            System.out.println(session.getAttribute("admin"));
            resp.sendRedirect("userIndex.jsp");
        }else {
            //登录失败
            LOGGER.warning("Login failed");
            session.setAttribute("msg","账号密码错误");
            resp.sendRedirect("index.jsp");
        }
    }
}
