package com.system.servlet;

import com.system.bean.Commodity;
import com.system.service.ICommodityService;
import com.system.service.impl.CommodityServiceImpl;
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

@WebServlet(name = "CommodityServlet" ,urlPatterns = "/commodityServlet")
public class CommodityServlet extends HttpServlet {

    private ICommodityService CommodityService = new CommodityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

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
            if (Constant.SERVLET_TYPE_SAVE.equals(type)||Objects.equals("update",type)) {
                // 添加报纸信息
                try {
                    saveOrUpdateCommodity(req, resp,type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (Constant.SERVLET_TYPE_DELETE.equals(type)){
                deleteCommodityByUser_id(req, resp);
            }else if (Constant.SERVLET_TYPE_QUERY.equals(type)){
                try {
                    queryCommodityCondition(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                // 根据id单条查询
                String Commodity_id = req.getParameter("commodity_id");
                Commodity commodity = CommodityService.queryByCommodity_id(Integer.parseInt(Commodity_id));
                // 跳转到更新页面同时保存数据
                req.setAttribute("commodity", commodity);
                req.getRequestDispatcher("/commodity/commodityUpdate.jsp").forward(req,resp);
            } else if ("buying".equals(type)) {
                // 购买商品,生成订单
                String Commodity_id = req.getParameter("Commodity_id");
                String user_id = req.getParameter("user_id");
                String count = req.getParameter("count");
                // System.out.println(Commodity_id+user_id+count);
                Commodity commodity = CommodityService.queryByCommodity_id(Integer.valueOf(Commodity_id));
                Integer flag = CommodityService.buyingCommodity(commodity,Integer.parseInt(Commodity_id),Integer.parseInt(user_id),Integer.parseInt(count));
                // System.out.println("生成购买订单"+flag);
                // 跳转到更新页面同时保存数据
                try {
                    queryCommodityAll(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            try {
                queryCommodityAll(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteCommodityByUser_id(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取需要删除的用户编号
        String Commodity_id = req.getParameter("commodity_id");
        // 通过Service处理删除操作
        Integer count = CommodityService.deleteByCommodity_id(Integer.parseInt(Commodity_id));
        // 做一个重定向查询所有用户
        resp.sendRedirect("commodityServlet");
    }

    /**
     * 查询所有报纸信息的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryCommodityAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        // 通过service查询所有的用户信息
        List<Commodity> list = CommodityService.getCommodity(null);
        // 把查询到的信息绑定在 request 作用域中
        req.setAttribute("list",list);
        System.out.println(list);
        try {
            req.getRequestDispatcher("/commodity/commodity.jsp").forward(req, resp);
        }catch (Exception e){
            System.out.println("asd");
        }
    }

    private void queryCommodityCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, Exception {
        // 通过service查询
        String str = req.getParameter("condition");
        List<Commodity> list = CommodityService.getCommodityCondition(str);
        // 把查询到的信息绑定在 request 作用域中
        req.setAttribute("list",list);
        System.out.println(list);
        req.getRequestDispatcher("/commodity/commodity.jsp").forward(req, resp);
    }

    private void saveOrUpdateCommodity(HttpServletRequest req, HttpServletResponse resp,String str) throws Exception {

        Commodity commodity = new Commodity();

        try {
            commodity = getRequestParameterForReflect(req, Commodity.class);
        }catch (Exception e){
            // 表示插入失败
            System.out.println("插入失败QAQ");
            // 跳转到失败页面
            resp.sendRedirect("/wrongOperate.jsp");
        }

        Integer count = -1;
        if (Objects.equals(str, "update")){
            // 表示是更新
            count = CommodityService.updateCommodity(commodity);
        }else {
            count = CommodityService.addCommodity(commodity);
        }
        if (count>0){
            // 查询成功，再做一次查询操作
            System.out.println("插入成功");
            resp.sendRedirect("commodityServlet");
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