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
    private boolean promotion;
    private Double vipDiscount;

    static DecimalFormat df = new DecimalFormat( "0.00");
    static DecimalFormat vip = new DecimalFormat( "0");

    public Item() {
        this.setName(null);
        this.setUnit(null);
        this.setPrice(0.00);
        this.setDiscount(1.0);
        this.setPromotion(false);
        this.setVipDiscount(1.0);
    }

    public Item(String name, String unit, Double price, Double discount, boolean promotion, Double vipDiscount) {
        this.setName(name);
        this.setUnit(unit);
        this.setPrice(price);
        this.setDiscount(discount);
        this.setPromotion(promotion);
        this.setVipDiscount(vipDiscount);
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
     * 获取 promotion
     * @return
     */
    public boolean getPromotion() {
        return this.promotion;
    }

    /**
     * 获取 vipDiscount
     * @return
     */
    public Double getVipDiscount() {
        return this.vipDiscount;
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

    /**
     * 设置 promotion
     * @param promotion
     */
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    /**
     * 设置 vipDiscount
     * @param vipDiscount
     */
    public void setVipDiscount(Double vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

}
