package com.pos.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLTable {

    /**
     * 数据库的表名和列名
     */
    private static final String TABLE = "items";
    private static final String BARCODE = "barcode";
    private static final String NAME = "name";
    private static final String UNIT = "unit";
    private static final String PRICE = "price";
    private static final String DISCOUNT = "discount";
    private static final String PROMOTION = "promotion";

    /**
     * 对表的操作语句
     */
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE
            + "(" + BARCODE + " VARCHAR(30) PRIMARY KEY ," + NAME
            + " VARCHAR(30) NOT NULL," + UNIT + " VARCHAR(30) NOT NULL,"
            + PRICE + " DOUBLE NOT NULL," + DISCOUNT + " DOUBLE ,"
            + PROMOTION + " INTEGER );";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE;

    private static Connection conn = null;
    private static Statement smt = null;


    /**
     * 创建表
     */
    public static boolean onCreate() {
        conn = new Connect().conn;

        try {
            smt = conn.createStatement();
            smt.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println("建表失败!");
            return false;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 先删除旧表,然后建立新表
     */
    public static boolean onUpgrade() {
        conn = new Connect().conn;

        try {
            smt = conn.createStatement();
            smt.executeUpdate(DROP_TABLE);
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