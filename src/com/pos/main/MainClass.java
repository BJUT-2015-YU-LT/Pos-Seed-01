package com.pos.main;

public class MainClass {

    public static void main(String[] args) {
        Cart cart = new Cart("./data/data2.json");

        cart.printAll();
    }
}
