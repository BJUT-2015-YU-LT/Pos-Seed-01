package com.pos.main;

public class MainClass {

    public String sayHello() {
        return "helloworld";
    }

    public static void main(String[] args) {
        Cart cart = new Cart("./data/data1.json");

        cart.printAll();
    }
}
