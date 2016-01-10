package com.pos.main;

import net.sf.json.JSONArray;
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
    private static final String batterryBar = "ITEM000004";

    private int colaNum = 0;
    private int spirteNum = 0;
    private int batterryNum = 0;

    private int freeCola = 0;
    private int freeSpirte = 0;
    private int freeBatterry = 0;

    private Item cola;
    private Item spirte;
    private Item batterry;

    private Double count;
    private Double reduce;

    private static Connection conn = null;
    private static Statement smt = null;

    /**
     * 构造函数, 将 Json 解析成对象,存到 list 中
     * @param path
     */
    public Cart(String path) {
        this.count = 0.00;
        this.reduce = 0.00;

        String barcode = "";
        String result = "";

        try {
            result = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray items = JSONArray.fromObject(result);

        for (int i = 0; i < items.size(); i++) {

            barcode = items.getString(i);
            if (barcode.equals(colaBar)) this.colaNum++;
            if (barcode.equals(spirteBar)) this.spirteNum++;
            if (barcode.equals(batterryBar)) this.batterryNum++;
        }
        queryDataBase(colaBar);
        queryDataBase(spirteBar);
        queryDataBase(batterryBar);

    }

    /**
     * 根据 barcode 查询商品信息
     * @param barcode
     */
    public void queryDataBase (String barcode) {
        Connection conn = new Connect().conn;
        Statement smt = null;
        ResultSet rs = null;
        String name = "";
        String unit = "";
        Double price = 0.00;
        Double discount = 1.0;
        boolean promotion = false;

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
                if (barcode.equals(colaBar)) cola = new Item(name, unit, price, discount, promotion);
                if (barcode.equals(spirteBar)) spirte = new Item(name, unit, price, discount, promotion);
                if (barcode.equals(batterryBar)) batterry = new Item(name, unit, price, discount, promotion);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印各类商品总价
     * @return
     */
    public Double printAll() {

        System.out.println("***商店购物清单***");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("打印时间：" + df.format(new Date()));
        System.out.println("----------------------");

        if (colaNum > 0) {
            this.count += print(colaNum, cola);
        }
        if (spirteNum > 0) {
            this.count += print(spirteNum, spirte);
        }
        if (batterryNum > 0) {
            this.count += print(batterryNum, batterry);
        }
        System.out.println("----------------------");
        System.out.println("挥泪赠送商品：");

        if (cola.getPromotion() && (freeCola = colaNum / 2) > 0) {
            System.out.println("名称：" + cola.getName() + "，数量：" + freeCola + cola.getUnit());
        }
        if (spirte.getPromotion() && (freeSpirte = spirteNum / 2) > 0) {
            System.out.println("名称：" + spirte.getName() + "，数量：" + freeSpirte + spirte.getUnit());
        }
        if (batterry.getPromotion() && (freeBatterry = batterryNum / 2) > 0) {
            System.out.println("名称：" + batterry.getName() + "，数量：" + freeBatterry + batterry.getUnit());
        }

        System.out.println("----------------------");
        System.out.println("总计：" + Item.df.format(this.count) + "(元)");
        System.out.println("节省："+ Item.df.format(this.reduce) + "(元)");
        System.out.println("**********************");

        this.addToDataBase();
        return this.reduce;
    }

    public void addToDataBase() {
        this.conn = new Connect().conn;

        String InsertSql =  "INSERT INTO logs( cola, spirte, batterry, freecola, freespirte, freebatterry, "
                + "count, reduce ) VALUES ( "
                + this.colaNum + ", " + this.spirteNum + ", " + this.batterryNum + ", "
                + this.freeCola + ", " + this.freeSpirte + ", " + this.freeBatterry + ", "
                + this.count + ", " + this.reduce + " );";

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
        int couple = size / 2;
        Double count = 0.00;

        String result = "";

        if (promotion) {
            count = (size - couple) * price;
            result = "名称：" + name
                    + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                    + "(元)，小计：" + Item.df.format(count) + "(元)";
            this.reduce += couple * price;
            System.out.println(result);
            return count;
        } else {
            count = size * price * discount;
            this.reduce += size * price * (1 - discount);
            result = "名称：" + name
                    + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                    + "(元)，小计：" + Item.df.format(count) + "(元)";
            System.out.println(result);
            return count;
        }
    }

}
