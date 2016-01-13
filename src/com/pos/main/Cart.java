package com.pos.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private static Map<String, Item> indexList = new HashMap<>();

    private List<Item> colaList = new ArrayList<>();
    private List<Item> spirteList = new ArrayList<>();
    private List<Item> batterryList = new ArrayList<>();

    private String user = "";
    private boolean isVip = false;
    private Double count;
    private Double reduce;
    private String list = "";

    /**
     * 构造函数, 将 Json 解析成对象,存到 list 中
     * @param path
     */
    public Cart(String index, String path) {
        this.count = 0.00;
        this.reduce = 0.00;

        String barcode= "";
        String name = "";

        String result ="";

        try {
            this.list = ReadFile.ReadFile(index);
            result = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.readIndex();

        JSONObject obj = JSONObject.fromObject(result);
        this.user = obj.getString("user");
        this.isVip = this.readVip(user);

        JSONArray items = JSONArray.fromObject(obj.getString("items"));


        for (int i = 0; i < items.size(); i++) {

            barcode = items.getString(i);

            Item item = this.indexList.get(barcode);
            name = item.getName();

            if ( name.equals("可口可乐") ) this.colaList.add(item);
            if ( name.equals("雪碧") ) this.spirteList.add(item);
            if ( name.equals("电池") ) this.batterryList.add(item);
        }

    }

    /**
     * 读取会员信息
     * @param user
     * @return
     */
    public boolean  readVip(String user) {
        String result ="";

        try {
            result = ReadFile.ReadFile("./data/vip.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = JSONObject.fromObject(result);
        JSONObject info = JSONObject.fromObject(obj.getString(user));

        return info.getBoolean("isVip");
    }

    /**
     * 读取索引文件
     * @return
     */
    public Map<String, Item> readIndex() {
        String name = "";
        String unit = "";
        Double price = 0.00;
        Double discount = 1.0;
        boolean promotion = false;
        Double vipDiscount = 1.0;

        JSONObject objs = JSONObject.fromObject(this.list);
        Iterator iterator = objs.keys();
        String key = "";

        while (iterator.hasNext()) {
            key = (String) iterator.next();

            JSONObject obj = JSONObject.fromObject(objs.getString(key));

            name = obj.getString("name");
            unit = obj.getString("unit");
            price = obj.getDouble("price");
            if (obj.containsKey("discount")) discount = obj.getDouble("discount");
            if (obj.containsKey("promotion")) promotion = obj.getBoolean("promotion");
            if (obj.containsKey("vipDiscount")) vipDiscount = obj.getDouble("vipDiscount");

            Item item = new Item(name, unit, price, discount, promotion, vipDiscount);

            this.indexList.put(key, item);
        }
        return this.indexList;
    }

    /**
     * 打印各类商品总价
     * @return
     */
    public Double printAll() {
        int colaNum = this.colaList.size();
        int spirteNum = this.spirteList.size();
        int batterryNum = this.batterryList.size();

        System.out.println("***商店购物清单***");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("打印时间：" + df.format(new Date()));
        System.out.println("----------------------");

        if (colaNum > 0) {
            Item item = this.colaList.get(0);
            this.count += print(colaNum, item);
        }
        if (spirteNum > 0) {
            Item item = this.spirteList.get(0);
            this.count += print(spirteNum, item);
        }
        if (batterryNum > 0) {
            Item item = this.batterryList.get(0);
            this.count += print(batterryNum, item);
        }

        int freeCola = colaNum / 3;
        int freeSpirte = spirteNum / 3;
        int freeBatterry = batterryNum / 3;
        if (freeCola > 0 || freeSpirte > 0 || freeBatterry > 0) {
            System.out.println("----------------------");
            System.out.println("挥泪赠送商品：");

            if (freeCola > 0) {
                Item item = this.colaList.get(0);
                System.out.println("名称：可口可乐，数量：" + freeCola + item.getUnit());
            }
            if (freeSpirte > 0) {
                Item item = this.spirteList.get(0);
                System.out.println("名称：雪碧，数量：" + freeSpirte + item.getUnit());
            }
            if (freeBatterry > 0) {
                Item item = this.batterryList.get(0);
                System.out.println("名称：电池，数量：" + freeBatterry + item.getUnit());
            }
        }

        System.out.println("----------------------");
        System.out.println("总计：" + Item.df.format(this.count) + "(元)");
        System.out.println("节省："+ Item.df.format(this.reduce) +"(元)");
        System.out.println("**********************");

        return this.reduce;
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
        Double vipDiscount = (this.isVip) ? item.getVipDiscount() : 1.0;
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
            count = size * price * discount * vipDiscount;
            this.reduce += size * price * (1 - discount * vipDiscount);
            result = "名称：" + name
                    + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                    + "(元)，小计：" + Item.df.format(count) + "(元)";
            System.out.println(result);
            return count;
        }

    }

}
