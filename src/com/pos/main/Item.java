package com.pos.main;

/**
 * Created by wangleqing on 16/1/6.
 */
public class Item {
    
    private String barcode;
    private String name;
    private String unit;
    private Double price;

    public String getBarcode() {

        return this.barcode;
    }

    public String getName() {
        return this.name;
    }

    public String getUnit() {
        return this.unit;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
