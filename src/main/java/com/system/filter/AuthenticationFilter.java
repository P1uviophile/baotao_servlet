package com.system.filter;

import com.system.utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截所有请求,判断当前是否是登录状态,是则访问,否则跳转登录界面
 */
@WebFilter(filterName = "authenticationFilter" ,urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求的地址
        String requestURI = request.getRequestURI();
        // 排除静态资源
        if (requestURI.endsWith(".css") || requestURI.endsWith(".js") || requestURI.endsWith(".png") || requestURI.endsWith(".jpg")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (requestURI.contains("index.jsp") || requestURI.contains("loginServlet") || requestURI.contains("Unauthorized.jsp")|| requestURI.contains("register.jsp")|| requestURI.contains("failRegister.jsp")|| requestURI.contains("userServlet")){
            // 访问登录页面直接放过
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            // 其他情况需要判断是否是登录状态
            HttpSession session = request.getSession();
            Object attribute = session.getAttribute(Constant.SESSION_LOGIN_USER);
            if (attribute != null){
                // 说明登录过了
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                // 未登录,需要跳转
                session.setAttribute("msg","请先登录");
                response.sendRedirect("Unauthorized.jsp");
            }
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
