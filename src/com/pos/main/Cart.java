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
        this.reduce += size * price * (1 - discount);

        String result = "名称：" + name
                + "，数量：" + size + unit  + "，单价：" + Item.df.format(price)
                + "(元)，小计：" + Item.df.format(size * price * discount) + "(元)";

        System.out.println(result);
        return size*price;
    }

}
