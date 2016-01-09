package com.pos.main;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private List<Item> colaList = new ArrayList<>();
    private List<Item> spirteList = new ArrayList<>();
    private List<Item> batterryList = new ArrayList<>();

    private Double count;

    /**
     * 构造函数, 将 Json 解析成对象,存到 list 中
     * @param path
     */
    public Cart(String path) {
        this.count = 0.00;

        String result = "";
        String bardcode = "";
        String name = "";
        String unit = "";
        Double price = 0.00;

        try {
            result = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray items = JSONArray.fromObject(result);

        for (int i = 0; i < items.size(); i++) {

            bardcode = items.getJSONObject(i).getString("barcode");
            name = items.getJSONObject(i).getString("name");
            unit =items.getJSONObject(i).getString("unit");
            price = items.getJSONObject(i).getDouble("price");

            Item item = new Item(bardcode, name, unit, price);

            if ( name.equals("可口可乐") ) this.colaList.add(item);
            if ( name.equals("雪碧") ) this.spirteList.add(item);
            if ( name.equals("电池") ) this.batterryList.add(item);
        }
    }

    /**
     * 打印各类商品总价
     * @return
     */
    public Double printAll() {
        System.out.println("***商店购物清单***");
        if (this.colaList.size() > 0) {
            Item item = this.colaList.get(0);
            this.count += print(item.getName(), this.colaList.size(), item.getPrice());
        }
        if (this.spirteList.size() > 0) {
            Item item = this.spirteList.get(0);
            this.count += print(item.getName(), this.spirteList.size(), item.getPrice());
        }
        if (this.batterryList.size() > 0) {
            Item item = this.batterryList.get(0);
            this.count += print(item.getName(), this.batterryList.size(), item.getPrice());
        }

        System.out.println("----------------------");
        System.out.println("总计：" + Item.df.format(this.count) + "(元)");
        System.out.println("**********************");
        return this.count;
    }

    /**
     * 分别打印输出商品信息
     * @param name
     * @param size
     * @param price
     * @return
     */
    public static Double print(String name, int size, Double price) {
        String result = "名称：" + name
                + "，数量：" + size + "瓶，单价：" + Item.df.format(price)
                + "(元)，小计：" + Item.df.format(size * price) + "(元)";
        System.out.println(result);
        return size*price;
    }

}
