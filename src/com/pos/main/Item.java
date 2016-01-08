package com.pos.main;

import java.text.DecimalFormat;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Item {

    private String name;
    private String unit;
    private Double price;
    private Double discount;

    static DecimalFormat df = new DecimalFormat( "0.00");

    public Item() {
        this.setName(null);
        this.setUnit(null);
        this.setPrice(0.00);
        this.setDiscount(1.0);
    }

    public Item(String name, String unit, Double price, Double discount) {
        this.setName(name);
        this.setUnit(unit);
        this.setPrice(price);
        this.setDiscount(discount);
    }

    /**
     * 获取 name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取 unit
     * @return
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * 获取 price
     * @return
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * 获取 discount
     * @return
     */
    public Double getDiscount() {
        return this.discount;
    }

    /**
     * 设置 name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置 unit
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 设置 price
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 设置 discount
     * @param discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
