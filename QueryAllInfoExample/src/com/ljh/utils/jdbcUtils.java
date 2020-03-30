package com.ljh.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ljh.service.impl.UserServiceImpl;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class jdbcUtils {
    private static DataSource ds;
    static {
        //这里一定要写在静态代码块里，不然会创建很多个连接池，一多了就挂
        Properties properties = new Properties();
        InputStream is = jdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static DataSource getDataSource() throws Exception {

        return ds;
    }
}
