package com.pos.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private List<String> itemsList = new ArrayList<>();

    private static final String colaBar = "ITEM000000";
    private static final String spirteBar = "ITEM000001";
    private static final String batterryBar = "ITEM000003";

    private Item cola;
    private Item spirte;
    private Item batterry;

    private int colaNum = 0;
    private int spirteNum = 0;
    private int batterryNum = 0;

    private User user = new User();
    private String userID = "";
    private Double count = 0.00;
    private Double reduce = 0.00;

    private static Connection conn = null;
    private static Statement smt = null;

    /**
     * 构造函数, 将 Json 解析成对象,存到 list 中
     * @param path
     */
    public Cart(String path) {
        String barcode = "";
        String result = "";

        try {
            result = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = JSONObject.fromObject(result);
        this.userID = obj.getString("user");
        this.user = MySQLOperate.queryUser(userID);

        JSONArray items = JSONArray.fromObject(obj.getString("items"));

        for (int i = 0; i < items.size(); i++) {
            barcode = items.getString(i);

            if (barcode.equals(this.colaBar)) this.colaNum++;
            if (barcode.equals(this.spirteBar)) this.spirteNum++;
            if (barcode.equals(this.batterryBar)) this.batterryNum++;
        }
        this.cola = MySQLOperate.queryDataBase(this.colaBar);
        this.spirte = MySQLOperate.queryDataBase(this.spirteBar);
        this.batterry = MySQLOperate.queryDataBase(this.batterryBar);
    }

    /**
     * 打印各类商品总价
     * @return
     */
    public boolean printAll() {

        if (colaNum > 0) {
            this.count += this.count(this.colaNum, this.cola);
        }
        if (spirteNum > 0) {
            this.count += this.count(this.spirteNum, this.spirte);
        }
        if (batterryNum > 0) {
            this.count += this.count(this.batterryNum, this.batterry);
        }

        this.user = User.addPoints(this.user, this.count);
        MySQLOperate.updatePoints(userID, user.getPoints());

        System.out.println("***商店购物清单***");
        System.out.println("会员编号：" + this.userID + "    " + "会员积分：" + user.getPoints() +"分");
        System.out.println("----------------------");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("打印时间：" + df.format(new Date()));
        System.out.println("----------------------");

        if (this.cola.getPromotion() && this.colaNum / 3 > 0) {
            System.out.println("名称：" + this.cola.getName() + "，数量：" + this.colaNum / 3 + this.cola.getUnit());
        }
        if (this.spirte.getPromotion() && this.spirteNum / 3 > 0) {
            System.out.println("名称：" + this.spirte.getName() + "，数量：" + this.spirteNum / 3 + this.spirte.getUnit());
        }
        if (this.batterry.getPromotion() && this.batterryNum / 3 > 0) {
            System.out.println("名称：" + this.batterry.getName() + "，数量：" + this.batterryNum / 3 + this.batterry.getUnit());
        }

        if (this.colaNum > 0) {
            this.print(this.colaNum, this.cola);
        }
        if (this.spirteNum > 0) {
            this.print(this.spirteNum, this.spirte);
        }
        if (this.batterryNum > 0) {
            this.print(this.batterryNum, this.batterry);
        }

        System.out.println("----------------------");
        System.out.println("总计：" + Item.df.format(this.count) + "(元)");
        System.out.println("节省："+ Item.df.format(this.reduce) + "(元)");
        System.out.println("**********************");

        this.addToLog();
        return true;
    }

    /**
     * 想数据库中添加一条数据
     */
    public void addToLog() {
        this.conn = new Connect().conn;

        String InsertSql =  "INSERT INTO logs ( user_id, cola, spirte, batterry, count, reduce ) VALUES ( "
                + "\"" + this.userID + "\", " + this.colaNum + ", " + this.spirteNum + ", "
                + this.batterryNum + ", " + Item.df.format(this.count) + ", "
                + Item.df.format(this.reduce) + " );";

        try {
            this.smt = conn.createStatement();
            this.smt.executeUpdate(InsertSql);
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
     * 计算总价
     * @param size
     * @param item
     * @return
     */
    public Double count(int size, Item item) {

        Double price = item.getPrice();
        Double discount = item.getDiscount();
        boolean promotion = item.getPromotion();
        Double vipDiscount = (this.user.getIsVip()) ? item.getVipDiscount() : 1.0;
        int couple = size / 3;
        Double count = 0.00;

        if (promotion) {
            count = (size - couple) * price;
        } else {
            count = size * price * discount * vipDiscount;
        }
        return count;
    }

    /**
     * 分别打印商品总价
     * @param size
     * @param item
     * @return
     */
    public Double print(int size, Item item) {
        String name = item.getName();
        String unit = item.getUnit();
        Double price = item.getPrice();
        Double discount = item.getDiscount();
        boolean promotion = item.getPromotion();
        Double vipDiscount = (this.user.getIsVip()) ? item.getVipDiscount() : 1.0;
        int couple = size / 3;
        Double count = 0.00;

        String result = "";

        if (promotion) {
            count = (size - couple) * price;
            result = "名称：" + name
                    + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                    + "(元)，小计：" + Item.df.format(count) + "(元)";
            this.reduce += couple * price;
            System.out.println(result);
        } else {
            count = size * price * discount * vipDiscount;
            this.reduce += size * price * (1 - discount * vipDiscount);
            result = "名称：" + name
                    + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                    + "(元)，小计：" + Item.df.format(count) + "(元)";
            System.out.println(result);
        }
        return count;
    }
}
