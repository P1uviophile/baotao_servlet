package com.system.servlet;

import com.system.HelloServlet;
import com.system.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注销的servlet
 */
@WebServlet(name = "logoutServlet",urlPatterns = "/logoutServlet")
public class LogoutServlet extends HelloServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //session.setAttribute(Constant.SESSION_LOGIN_USER,null);
        session.invalidate();//注销的方法
        resp.sendRedirect("index.jsp");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.doPost(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
