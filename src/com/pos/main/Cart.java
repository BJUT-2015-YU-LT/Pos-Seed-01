package com.pos.main;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Cart {

    private static List<Item> itemList = new ArrayList<Item>();
    private String path;
    private Double count;

    /**
     *
     *
     * @param path
     */
    public Cart(String path) {
        this.path = path;
        this.count = 0.0;
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
    public Double count() {
        this.dealJson();

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            this.count += item.getPrice();
        }
        return count;
    }
}
