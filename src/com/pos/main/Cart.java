package com.pos.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private static Map<String, Item> indexList = new HashMap<>();

    private List<Item> colaList = new ArrayList<>();
    private List<Item> spirteList = new ArrayList<>();
    private List<Item> batterryList = new ArrayList<>();

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

        JSONArray items = JSONArray.fromObject(result);

        for (int i = 0; i < items.size(); i++) {

            barcode = items.getString(i);

            Item item = indexList.get(barcode);
            name = item.getName();

            if ( name.equals("可口可乐") ) colaList.add(item);
            if ( name.equals("雪碧") ) spirteList.add(item);
            if ( name.equals("电池") ) batterryList.add(item);
        }

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

        JSONObject objs = JSONObject.fromObject(this.list);
        Iterator iterator = objs.keys();
        String key = "";

        while (iterator.hasNext()) {
            key = (String) iterator.next();

            JSONObject obj = JSONObject.fromObject(objs.getString(key));

            name = obj.getString("name");
            unit = obj.getString("unit");
            price = obj.getDouble("price");
            Item item = new Item(name, unit, price, discount);

            this.indexList.put(key, item);
        }
        return indexList;
    }

    /**
     * 打印各类商品总价
     * @return
     */
    public Double printAll() {
        System.out.println("***商店购物清单***");
        if (this.colaList.size() > 0) {
            Item item = this.colaList.get(0);
            this.count += print(this.colaList.size(), item);
        }
        if (this.spirteList.size() > 0) {
            Item item = this.spirteList.get(0);
            this.count += print(this.spirteList.size(), item);
        }
        if (this.batterryList.size() > 0) {
            Item item = this.batterryList.get(0);
            this.count += print(this.batterryList.size(), item);
        }

        System.out.println("----------------------");
        System.out.println("总计：" + Item.df.format(this.count - this.reduce) + "(元)");
        System.out.println("节省："+ Item.df.format(this.reduce) +"(元)");
        System.out.println("**********************");

        return this.count;
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
        this.reduce += size * price * (1 - discount);

        String result = "名称：" + name
                + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                + "(元)，小计：" + Item.df.format(size * price * discount) + "(元)";

        System.out.println(result);
        return size*price;
    }

}
