package com.pos.main;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private List<Item> colaList = new ArrayList<Item>();
    private List<Item> spirteList = new ArrayList<Item>();
    private List<Item> batterryList = new ArrayList<Item>();

    private Double count;
    private Double reduce;

    /**
     * 构造函数, 将 Json 解析成对象,存到 list 中
     * @param path
     */
    public Cart(String path) {
        this.count = 0.00;
        this.reduce = 0.00;

        String result = "";
        String bardcode = "";
        String name = "";
        String unit = "";
        Double price = 0.00;
        Double discount = 1.0;

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
            discount = items.getJSONObject(i).getDouble("discount");

            Item item = new Item(bardcode, name, unit, price, discount);

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
            count += print(colaList.size(), item);
            reduce = count - item.getDiscount()*count;
        }
        if (spirteList.size() > 0) {
            Item item = spirteList.get(0);
            count += print(spirteList.size(), item);
            reduce = count - item.getDiscount()*count;
        }
        if (batterryList.size() > 0) {
            Item item = batterryList.get(0);
            count += print(batterryList.size(), item);
            reduce = count - item.getDiscount()*count;
        }

        System.out.println("----------------------");
        System.out.println("总计：" + Item.df.format(count - reduce) + "(元)\n");
        System.out.println("节省："+ Item.df.format(reduce) +"(元)\n");
        System.out.println("**********************\n");

        return count - reduce;
    }

    public static Double print(int size, Item item) {
        String name = item.getName();
        String unit = item.getUnit();
        Double price = item.getPrice();
        Double discount = item.getDiscount();

        String result = "名称：" + name
                + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                + "(元)，小计：" + Item.df.format(size * price * discount) + "(元)";

        System.out.println(result);
        return size*price;
    }

}
