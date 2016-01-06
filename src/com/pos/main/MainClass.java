package com.pos.main;

public class MainClass {

    public String sayHello() {
        return "helloworld";
    }

    public static void main(String[] args) {
        Cart.dealJson("./data/data1.json");
    }
}
