package com.pos.main;

public class MainClass {

    public static void main(String[] args) {
        Cart cart = new Cart("./data/data1.json");

        cart.printAll();
    }
}
