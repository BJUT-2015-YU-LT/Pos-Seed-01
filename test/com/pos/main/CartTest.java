package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class CartTest {

    private Cart cart;
    private String list;
    private String path;

    @Before
    public void setUp() throws Exception {
        this.list = "./data/data5_6_1.json";
        this.path = "./data/data5_6_2.json";
        this.cart = new Cart(list, path);
    }

    /**
     * 测试所有商品节省的价钱
     * @throws Exception
     */
    @Test
    public void testPrintAll() throws Exception {
        Double result = 6.32;
        assertEquals(result, this.cart.printAll());
    }

    /**
     * 测试打印某个商品的总价
     * @throws Exception
     */
    @Test
    public void testPrint() throws Exception {
        Item itemDiscount = new Item("电池", "个", 2.00, 0.8, false, 0.8);
        Item itemPromotion = new Item("电池", "个", 2.00, 1.0, true, 1.0);
        int size = 3;
        Double result1 = size * 2.00 * 0.8 * 0.8;
        Double result2 = (size - 1) * 2.00;
        assertEquals(result1, this.cart.print(size, itemDiscount));
        assertEquals(result2, this.cart.print(size, itemPromotion));
    }

}

