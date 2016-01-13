package com.pos.main;

public class MainClass {

    public static void main(String[] args) {
        MySQLTable.onUpgrade();
        String path = "./data/Cart.json";
        Cart cart = new Cart(path);

        cart.printAll();
    }
}
