package com.pos.main;

public class MainClass {

    public static void main(String[] args) {
        String index = "./data/data5_6_1.json";
        String path = "./data/data5_6_2.json";
        Cart cart = new Cart(index, path);

        cart.printAll();
    }
}
