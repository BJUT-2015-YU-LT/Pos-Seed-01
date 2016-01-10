package com.pos.main;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLOperate {

    private static Connection conn = null;
    private static Statement smt = null;

    /**
     * 读取索引文件, 存入数据库
     * @return
     */
    public static boolean readIndex() {
        String index = "";
        String path = "./data/index.json";

        String name = "";
        String unit = "";
        Double price = 0.00;
        Double discount = 1.0;
        boolean promotion = false;

        try {
            index = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject objs = JSONObject.fromObject(index);
        Iterator iterator = objs.keys();
        String barcode = "";
        String sql = "";

        while (iterator.hasNext()) {
            barcode = (String) iterator.next();

            JSONObject obj = JSONObject.fromObject(objs.getString(barcode));

            name = obj.getString("name");
            unit = obj.getString("unit");
            price = obj.getDouble("price");
            discount = obj.getDouble("discount");
            promotion = obj.getBoolean("promotion");
            Item item = new Item(name, unit, price, discount, promotion);

            sql = setInsert(barcode, item);
            insert(sql);
        }
        return true;
    }

    /**
     * 构造插入语句
     * @param barcode
     * @param item
     * @return
     */
    public static String setInsert(String barcode, Item item) {
        String sql = "INSERT INTO items VALUES ( \""
                + barcode + "\", \"" + item.getName() + "\", \"" + item.getUnit() + "\", "
                + item.getPrice() + ", " + item.getDiscount() + ", " + item.getPromotion() + " );";

        return sql;
    }

    /**
     * 插入函数
     * @param sql
     */
    public static void insert(String sql) {
        conn = new Connect().conn;

        try {
            smt = conn.createStatement();
            smt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("插入数据失败!");
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
