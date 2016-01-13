package com.pos.main;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
    public static boolean readItem() {
        String index = "";
        String path = "./data/Item_index.json";

        String name = "";
        String unit = "";
        Double price = 0.00;
        Double discount = 1.0;
        boolean promotion = false;
        Double vipDiscount = 1.0;

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
            if (obj.containsKey("discount")) discount = obj.getDouble("discount");
            if (obj.containsKey("promotion")) promotion = obj.getBoolean("promotion");
            if (obj.containsKey("vipDiscount")) vipDiscount = obj.getDouble("vipDiscount");
            Item item = new Item(name, unit, price, discount, promotion, vipDiscount);

            sql = insertItem(barcode, item);
            insert(sql);
        }
        return true;
    }

    /**
     * 读取用户信息存到数据库中
     * @return
     */
    public static boolean readUser() {
        String index = "";
        String path = "./data/User_index.json";

        String name = "";
        boolean isVip = false;
        int points = 0;

        try {
            index = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject objs = JSONObject.fromObject(index);
        Iterator iterator = objs.keys();
        String id = "";
        String sql = "";

        while (iterator.hasNext()) {
            id = (String) iterator.next();

            JSONObject obj = JSONObject.fromObject(objs.getString(id));

            name = obj.getString("name");
            isVip = obj.getBoolean("isVip");

            User user = new User(name, isVip, points);
            sql = insertUser(id, user);
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
    public static String insertItem(String barcode, Item item) {
        String sql = "INSERT INTO items VALUES ( \""
                + barcode + "\", \"" + item.getName() + "\", \"" + item.getUnit() + "\", "
                + item.getPrice() + ", " + item.getDiscount() + ", "
                + item.getPromotion() +  ", " + item.getVipDiscount() + " );";
        return sql;
    }

    /**
     * 构造插入语句
     * @param id
     * @param user
     * @return
     */
    public static String insertUser(String id, User user) {
        String sql = "INSERT INTO users VALUES ( \""
                + id + "\", \"" + user.getName() + "\", " + user.getIsVip() + ", " + user.getPoints() + " );";
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

    /**
     * 从数据库获取用户积分
     * @param id
     * @return
     */
    public static User queryUser(String id) {
        String sql = "SELECT * FROM users WHERE `id` = \"" + id +"\";";

        conn = new Connect().conn;
        ResultSet rs = null;

        User user = new User();
        String name = "";
        boolean isVip = false;
        int points = 0;

        try {
            smt = conn.createStatement();
            rs = smt.executeQuery(sql);

            while(rs.next())
            {
                name = rs.getString("name");
                isVip = rs.getBoolean("isVip");
                points = rs.getInt("points");

                user = new User(name, isVip, points);
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 在数据库中修改积分
     * @param id
     * @param points
     * @return
     */
    public static boolean updatePoints(String id, int points) {
        String sql = "UPDATE users SET `points` = " + points + " WHERE `id` = \"" + id + "\";";
        insert(sql);

        return true;
    }

    /**
     * 根据 barcode 查询商品信息
     * @param barcode
     */
    public static Item queryDataBase(String barcode) {
        Connection conn = new Connect().conn;
        Statement smt = null;
        ResultSet rs = null;
        String name = "";
        String unit = "";
        Double price = 0.00;
        Double discount = 1.0;
        boolean promotion = false;
        Double vipDiscount = 1.0;

        Item item = new Item();
        String querySql = "select * from items where `barcode` = '" + barcode + "';";

        try {
            smt = conn.createStatement();
            rs = smt.executeQuery(querySql);

            while(rs.next())
            {
                name = rs.getString("name");
                unit = rs.getString("unit");
                price = rs.getDouble("price");
                discount = rs.getDouble("discount");
                promotion = rs.getBoolean("promotion");
                vipDiscount = rs.getDouble("vipDiscount");

                item = new Item(name, unit, price, discount, promotion, vipDiscount);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
