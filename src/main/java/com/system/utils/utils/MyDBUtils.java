package com.system.utils.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;

/*
        数据库的工具类
 */
public class MyDBUtils {

    private static MysqlDataSource dataSource;

    static {
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("jk18889903808");
        dataSource.setUrl("jdbc:mysql://localhost:3306/baotao?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
    }

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
