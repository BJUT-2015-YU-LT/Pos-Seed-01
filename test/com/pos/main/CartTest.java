package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class CartTest {

    private Cart cart;
    private String path;

    @Before
    public void setUp() throws Exception {
        path = "./data/data2.json";
    }

    /**
     * 测试打印所有商品价格
     * @throws Exception
     */
    @Test
    public void testPrintAll() throws Exception {
        cart = new Cart(path);
        Double discount = 0.40;
        assertEquals(discount, cart.printAll());
    }

    /**
     * 测试打印某个商品的总价
     * @throws Exception
     */
    @Test
    public void testPrint() throws Exception {
        String name = "可口可乐";
        int size = 2;
        Double price = 3.0;
        Double result = size*price;

        assertEquals(result, cart.print(name, size, price));
    }
}

