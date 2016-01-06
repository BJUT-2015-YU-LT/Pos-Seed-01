package com.pos.main;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class Item {
    
    private String barcode;
    private String name;
    private String unit;
    private Double price;

    /**
     * 获取 barcode
     * @return
     */
    public String getBarcode() {

        return this.barcode;
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
     * 设置 barcode
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
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


}
