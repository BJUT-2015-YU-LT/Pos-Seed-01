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
        this.path = "./data/data1.json";
        this.cart = new Cart(this.path);
    }

    /**
     * 测试打印所有商品价格
     * @throws Exception
     */
    @Test
    public void testPrintAll() throws Exception {
        Double result = 23.00;
        assertEquals(result, this.cart.printAll());
    }

    /**
     * 测试打印某个商品的总价
     * @throws Exception
     */
    @Test
    public void testPrint() throws Exception {
        Item item = new Item("ITEM000000", "可口可乐", "瓶", 3.00);
        int size = 2;
        Double result = size*item.getPrice();

        assertEquals(result, this.cart.print(size, item));
    }
}

