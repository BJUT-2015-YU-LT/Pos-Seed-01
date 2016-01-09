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
        this.path = "./data/data2.json";
        this.cart = new Cart(this.path);

    }

    /**
     * 测试打印所有商品价格
     * @throws Exception
     */
    @Test
    public void testPrintAll() throws Exception {
        Double result = 1 * 2.00 * (1 - 0.8);
        assertEquals(result, this.cart.printAll());
    }

    /**
     * 测试打印某个商品的总价
     * @throws Exception
     */
    @Test
    public void testPrint() throws Exception {
        Item item = new Item("ITEM000004", "电池", "个", 2.00, 0.8);
        int size = 2;
        Double result = size * 2.00 * 0.8;
        assertEquals(result, this.cart.print(size, item));
    }
}

