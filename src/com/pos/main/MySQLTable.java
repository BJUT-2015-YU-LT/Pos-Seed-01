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
    private static final String ITEM_TABLE = "items";
    private static final String BARCODE = "barcode";
    private static final String NAME = "name";
    private static final String UNIT = "unit";
    private static final String PRICE = "price";
    private static final String DISCOUNT = "discount";
    private static final String PROMOTION = "promotion";

    private static final String LOG_TABLE = "logs";
    private static final String ID = "id";
    private static final String COLA = "cola";
    private static final String SPIRTE = "spirte";
    private static final String BATTERRY = "batterry";
    private static final String FREECOLA = "freecola";
    private static final String FREESPIRTE = "freespirte";
    private static final String FREEBATTERRY = "freebatterry";
    private static final String COUNT = "count";
    private static final String REDUCE = "reduce";
    private static final String TIME = "time";

    /**
     * 对表的操作语句
     */
    private static final String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE
            + " ( " + BARCODE + " VARCHAR(30) PRIMARY KEY, " + NAME
            + " VARCHAR(30) NOT NULL, " + UNIT + " VARCHAR(30) NOT NULL, "
            + PRICE + " DOUBLE NOT NULL, " + DISCOUNT + " DOUBLE, "
            + PROMOTION + " BOOLEAN ) DEFAULT CHARSET=UTF8;";

    private static final String CREATE_LOG_TABLE = "CREATE TABLE IF NOT EXISTS " + LOG_TABLE
            + " ( " + ID + " INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT, " + COLA
            + " INTEGER(16) NOT NULL, "  + SPIRTE + " INTEGER(16) NOT NULL, " + BATTERRY
            + " INTEGER(16) NOT NULL, "+ FREECOLA + " INTEGER(16) NOT NULL, "  + FREESPIRTE
            + " INTEGER(16) NOT NULL, " + FREEBATTERRY + " DOUBLE NOT NULL, " + COUNT
            + " DOUBLE NOT NULL, " + REDUCE + " DOUBLE NOT NULL, " + TIME +" TIMESTAMP ) DEFAULT CHARSET=UTF8;";

    private static final String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS " + ITEM_TABLE;

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
            smt.executeUpdate(CREATE_LOG_TABLE);
            MySQLOperate.readIndex();
        } catch (SQLException e) {
            System.out.println("建表失败!");
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
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