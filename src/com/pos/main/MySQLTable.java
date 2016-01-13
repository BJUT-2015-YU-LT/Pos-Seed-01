package com.pos.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLTable {

    /**
     * 对表的操作语句
     */
    private static final String CREATE_ITEM_TABLE = "CREATE TABLE items ( " +
            "barcode VARCHAR(30) NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL, " +
            "unit VARCHAR(30) NOT NULL, price DOUBLE NOT NULL, " +
            "discount DOUBLE, promotion BOOLEAN, vipDiscount DOUBLE ) DEFAULT CHARSET=UTF8;";

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users ( " +
            "id VARCHAR(30) NOT NULL PRIMARY KEY, name VARCHAR(30) NOT NULL, " +
            "isVip BOOLEAN, points INTEGER(16) ) DEFAULT CHARSET=UTF8;";

    private static final String CREATE_LOG_TABLE = "CREATE TABLE IF NOT EXISTS logs ( " +
            "id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT, user_id VARCHAR(30) NOT NULL, " +
            "cola INTEGER(16) NOT NULL, spirte INTEGER(16) NOT NULL, batterry INTEGER(16) NOT NULL, "+
            "count DOUBLE NOT NULL, reduce DOUBLE NOT NULL, time TIMESTAMP ) DEFAULT CHARSET=UTF8;";

    private static final String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS items";

    private static Connection conn = null;
    private static Statement smt = null;

    /**
     * 创建表
     */
    public static void onCreate() {
        conn = new Connect().conn;

        try {
            smt = conn.createStatement();
            smt.executeUpdate(CREATE_ITEM_TABLE);
            smt.executeUpdate(CREATE_USER_TABLE);
            smt.executeUpdate(CREATE_LOG_TABLE);
            MySQLOperate.readItem();
            loadUser();
        } catch (SQLException e) {
            System.out.println("建表失败!");
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadUser() {
        conn = new Connect().conn;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM users;";

        try {
            smt = conn.createStatement();
            rs = smt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            while(rs.next())
            {
                count = rs.getInt(1);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count == 0) {
            MySQLOperate.readUser();
        }
    }

    /**
     * 先删除旧表,然后建立新表
     */
    public static boolean onUpgrade() {
        conn = new Connect().conn;

        try {
            smt = conn.createStatement();
            smt.executeUpdate(DROP_ITEM_TABLE);
        } catch (SQLException e) {
            System.out.println("删除旧表失败!");
            return false;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate();
        return true;
    }

}