package com.system.servlet;

import com.system.bean.Orders;
import com.system.service.IOrdersService;
import com.system.service.impl.OrdersServiceImpl;
import com.system.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.*;
import java.util.Objects;

@WebServlet(name = "ordersServlet" ,urlPatterns = "/ordersServlet")
public class OrdersServlet extends HttpServlet {

    private IOrdersService ordersService = new OrdersServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter(Constant.REQUEST_PARAMETER);
        if (type != null && !"".equals(type)){
            if (Constant.SERVLET_TYPE_SAVE.equals(type)) {
                // 添加信息
                try {
                    saveOrUpdateOrders(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (Constant.SERVLET_TYPE_DELETE.equals(type)){
                deleteOrdersByUser_id(req, resp);
            }else if (Constant.SERVLET_TYPE_QUERY.equals(type)){
                try {
                    queryOrdersCondition(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                // 根据id单条查询
                String orders_id = req.getParameter("orders_id");
                Orders orders = ordersService.queryByOrders_id(Integer.parseInt(orders_id));
                // 跳转到更新页面同时保存数据
                req.setAttribute("orders",orders);
                req.getRequestDispatcher("orders/ordersUpdate.jsp").forward(req,resp);
            }
        }else {
            queryOrdersAll(req, resp);
        }
    }

    private void deleteOrdersByUser_id(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取需要删除的用户编号
        String orders_id = req.getParameter("orders_id");
        // 通过Service处理删除操作
        Integer count = ordersService.deleteByOrders_id(Integer.parseInt(orders_id));
        // 做一个重定向查询所有用户
        resp.sendRedirect("ordersServlet");
    }

    /**
     * 查询所有报纸信息的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryOrdersAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过service查询所有的信息
        List<Orders> list = ordersService.getOrders(null);
        // 把查询到的信息绑定在 request 作用域中
        req.setAttribute("list",list);

        req.getRequestDispatcher("orders/orders.jsp").forward(req, resp);
    }

    private void queryOrdersCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        // 通过service查询
        String str = req.getParameter("condition");
        List<Orders> list = ordersService.getOrdersCondition(str);
        // 把查询到的信息绑定在 request 作用域中
        req.setAttribute("list",list);
        // 页面跳转到user.jsp中
        req.getRequestDispatcher("orders/orders.jsp").forward(req, resp);
    }

    private void saveOrUpdateOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Orders orders = new Orders();

        try {
            orders.setClient_id(Integer.valueOf(req.getParameter("client_id")));
            if (!Objects.equals(req.getParameter("order_id"), null))
                orders.setOrder_id(Integer.valueOf(req.getParameter("order_id")));
            else
                orders.setOrder_id(null);
            orders.setCommodity_id(Integer.parseInt(req.getParameter("Commodity_id")));
            orders.setFinish(req.getParameter("finish"));
            orders.setCount(Integer.parseInt(req.getParameter("count")));
            orders.setPrice(Integer.parseInt(req.getParameter("price")));
            System.out.println(orders);
        } catch (Exception e) {
            // 表示插入失败
            System.out.println("插入失败QAQ");
            // 跳转到失败页面
            resp.sendRedirect("wrongOperate.jsp");
            e.printStackTrace();
        }

        Integer count = -1;
        if (orders.getOrder_id() != null && orders.getOrder_id() > 0){
            // 表示是更新
            count = ordersService.updateOrders(orders);
        }else {
            count = ordersService.addOrders(orders);
        }
        if (count>0){
            // 查询成功，再做一次查询操作
            resp.sendRedirect("ordersServlet");
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
