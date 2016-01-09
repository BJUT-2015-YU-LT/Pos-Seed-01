package com.pos.main;

public class MainClass {

    public static void main(String[] args) {
        String index = "./data/data3_1.json";
        String path = "./data/data3_2.json";
        Cart cart = new Cart(index, path);

        //cart.printAll();
    }
}
