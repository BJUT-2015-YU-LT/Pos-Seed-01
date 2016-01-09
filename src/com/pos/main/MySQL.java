package com.pos.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pengzhendong on 16/1/9.
 */
public class MySQL {

    Env env =  new Env();
    String driver = env.getProperty("DRIVER");
    String url = env.getProperty("URL");
    String user = env.getProperty("USER");
    String password =env.getProperty("PASSWORD");
    public Connection conn;

    public MySQL() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败!");
        }
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("连接失败,请检查用户名和密码!");
        }
    }
}
