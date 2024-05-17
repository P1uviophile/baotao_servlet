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
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户的Servlet
 * 作用：接收请求 --> 通过Service处理请求 --> 响应请求
 */
@WebServlet(name = "UserServlet" , urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {

    // 声明IUserService的对象
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 统一处理浏览器提交的 <a href="http://localhost:8080/userServlet">...</a> 的请求
     * @param req 封装请求相关信息的对象
     * @param resp 封装相应相关信息的对象
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter(Constant.REQUEST_PARAMETER);
        // 获取所有参数的名称
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println(req.getParameter("type"));
        // 遍历参数名称并输出参数名和对应的值
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            System.out.println("参数名: " + paramName + ", 参数值: " + paramValue);
        }
        if (type != null && !"".equals(type)){
            if (Constant.SERVLET_TYPE_SAVE.equals(type)){
                
                // 添加用户信息
                try {
                    saveOrUpdateUser(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (Constant.SERVLET_TYPE_UPDATE.equals(type)) {
                // 更新用户信息
            } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
                // 删除用户信息
                deleteUserByUser_id(req, resp);
            } else if (Constant.SERVLET_TYPE_QUERY.equals(type)) {
                // 条件查询用户信息
                try {
                    queryUserCondition(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)) {
                // 根据id单条查询
                String user_id = req.getParameter("user_id");
                User user = userService.queryByUser_id(Integer.parseInt(user_id));
                // 跳转到更新页面同时保存数据
                req.setAttribute("user",user);
                req.getRequestDispatcher("user/userUpdate.jsp").forward(req,resp);
            }else if (type.equals("register")){
                String userName = req.getParameter("user_name");
                String password = req.getParameter("password");
                boolean flag = userService.checkUserName(userName, password);
                if (flag){ // 存在相同用户名
                    resp.sendRedirect("failRegister.jsp");
                }else {
                    User user =new User();
                    user.setPassword(password);
                    user.setUser_name(userName);
                    user.setAdmin(0);
                    System.out.println(user);
                    int count = userService.addUser(user);
                    if (count>0){
                        System.out.println("插入成功");
                        resp.sendRedirect("index.jsp");
                    }else {
                        // 表示插入失败
                        System.out.println("插入失败QAQ");
                        // 跳转到失败页面
                        resp.sendRedirect("wrongOperate.jsp");
                    }
                }
            }
        }else {
            // 默认查询用户信息
            queryUserAll(req, resp);
        }
    }

    private void deleteUserByUser_id(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取需要删除的用户编号
        String user_id = req.getParameter("user_id");
        // 通过Service处理删除操作
        Integer count = userService.deleteByUser_id(Integer.parseInt(user_id));
        System.out.println(count);
        // 做一个重定向查询所有用户
        resp.sendRedirect("userServlet");
    }

    /**
     * 查询所有用户信息的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryUserAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过service查询所有的用户信息
        List<User> list = userService.getUser(null);
        // 把查询到的信息绑定在 request 作用域中
        req.setAttribute("list",list);
        // 页面跳转到user.jsp中
        req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }

    private void queryUserCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        // 通过service查询
        String str = req.getParameter("condition");
        List<User> list = userService.getUserCondition(str);
        // 把查询到的信息绑定在 request 作用域中
        req.setAttribute("list",list);
        // 页面跳转到user.jsp中
        System.out.println(list);
        req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }

    /**
     * 添加用户的方法
     * @param req
     * @param resp
     * @throws IOException
     */
    private void saveOrUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取用户表单提交信息
        /*User user = new User();
        user.setUser_name(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));*/

        // User user = getRequestParameterForReflect(req, User.class);
        User user = new User();
        user.setUser_name(req.getParameter("user_name"));
        if(!Objects.equals(req.getParameter("user_id"), ""))
            user.setUser_id(Integer.valueOf(req.getParameter("user_id")));
        else user.setUser_id(null);
        user.setAdmin(Integer.valueOf(req.getParameter("admin")));
        user.setPassword(req.getParameter("password"));

        Integer count = -1;
        if (user.getUser_id() != null){
            // 表示是更新
            System.out.println(user.getUser_id());
            count = userService.updateUser(user,req.getParameter("user_name_root"));
        }else {
            count = userService.addUser(user);
        }
        if (count>0){
            System.out.println("插入成功");
            // 查询成功，再做一次查询操作
            resp.sendRedirect("userServlet");
        }else {
            // 表示插入失败
            System.out.println("插入失败QAQ");
            // 跳转到失败页面
            resp.sendRedirect("wrongOperate.jsp");
        }

    }

    // 反射应用
    private <T> T getRequestParameterForReflect(HttpServletRequest req, Class<T> cls) throws Exception{
        T t = cls.newInstance();
        Map<String,String[]> parameterMap = req.getParameterMap();
        Field[] declareFields = cls.getDeclaredFields();
        if (declareFields != null && declareFields.length > 0){
            for (Field declareField : declareFields) {
                String[] values = parameterMap.get(declareField.getName());
                if (values == null || values.length == 0){
                    continue;
                }
                if (declareField.getType() == String[].class){
                    // 判断是否是数组
                    declareField.setAccessible(true); //放开访问
                    try {
                        declareField.set(t,values);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declareField.setAccessible(false);
                    continue;
                }
                if (declareField.getType() == Integer.class){
                    declareField.setAccessible(true); //放开访问
                    try {
                        declareField.set(t,Integer.parseInt(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declareField.setAccessible(false);
                    continue;
                }
                // 普通数据
                declareField.setAccessible(true); //放开访问
                try {
                    declareField.set(t,values[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                declareField.setAccessible(false);
                continue;
            }
        }
        return t;
    }
}
