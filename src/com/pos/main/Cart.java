package com.pos.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangleqing on 16/1/6.
 */
public class Cart {

    private static List<Item> itemList = new ArrayList<Item>();
    private String path;
    private Double count;
    private Double cola;
    private Double battery;
    private Double sprite;
    /**
     *
     *
     * @param path
     */
    public Cart(String path) {
        this.path = path;
        this.count = 0.00;
        this.cola=0.00;
        this.battery=0.00;
        this.sprite=0.00;


    }

    /**
     *
     * @return
     */
    public boolean dealJson() {
        String result = "";
        try {
            result = ReadFile.ReadFile(this.path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        JSONArray items = JSONArray.fromObject(result);

        for (int i = 0; i < items.size(); i++) {
            Item item = new Item();

            item.setBarcode(items.getJSONObject(i).getString("barcode"));
            item.setName(items.getJSONObject(i).getString("name"));
            item.setUnit(items.getJSONObject(i).getString("unit"));
            item.setPrice(items.getJSONObject(i).getDouble("price"));

            itemList.add(item);
        }
        return true;
    }
    /**
     *
     *
     * @return
     */
    public double count() {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            this.count += item.getPrice();
            if(item.getName().equals("可口可乐")){
                cola++;
            }
            if(item.getName().equals("雪碧")){
                sprite++;
            }
            if(item.getName().equals("电池")){
                battery++;
            }
        }
        System.out.println("可口可乐"+String.format("%.0f",cola)+"瓶"+"小计:"+String.format("%.2f",3*cola)+"元");
        System.out.println("雪碧"+sprite+"瓶"+"小计:"+String.format("%.2f",3*sprite)+"元");
        System.out.println("电池"+battery+"个"+"小计:"+String.format("%.2f",0.8*battery)+"元");
        return  count;
    }

}
