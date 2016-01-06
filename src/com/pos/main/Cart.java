package com.pos.main;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private static List<Item> colaList = new ArrayList<Item>();
    private static List<Item> spirteList = new ArrayList<Item>();
    private static List<Item> batterryList = new ArrayList<Item>();

    private Double count;

    /**
     * 构造函数, 将 Json 解析成对象,存到 list 中
     * @param path
     */
    public Cart(String path) {
        this.count = 0.0;

        String result = "";
        String bardcode = "";
        String name = "";
        String unit = "";
        Double price = 0.0;

        try {
            result = ReadFile.ReadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray items = JSONArray.fromObject(result);

        for (int i = 0; i < items.size(); i++) {
            Item item = new Item();

            bardcode = items.getJSONObject(i).getString("barcode");
            name = items.getJSONObject(i).getString("name");
            unit =items.getJSONObject(i).getString("unit");
            price = items.getJSONObject(i).getDouble("price");

            item.setBarcode(bardcode);
            item.setName(name);
            item.setUnit(unit);
            item.setPrice(price);

            if ( name.equals("可口可乐") ) colaList.add(item);
            if ( name.equals("雪碧") ) spirteList.add(item);
            if ( name.equals("电池") ) batterryList.add(item);
        }
    }

    /**
     * 打印各类商品总价
     * @return
     */
    public Double printAll() {
        System.out.println("***商店购物清单***");
        if (colaList.size() > 0) {
            Item item = colaList.get(0);
            count += print(item.getName(), colaList.size(), item.getPrice());
        }
        if (spirteList.size() > 0) {
            Item item = spirteList.get(0);
            count += print(item.getName(), spirteList.size(), item.getPrice());
        }
        if (batterryList.size() > 0) {
            Item item = batterryList.get(0);
            count += print(item.getName(), batterryList.size(), item.getPrice());
        }

        System.out.println("----------------------\n" + "总计：" + count + "(元)\n**********************");
        return count;
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
                + "，数量：" + size + "瓶，单价：" + price
                + "(元)，小计：" + size * price + "(元)";
        System.out.println(result);
        return size*price;
    }

}
