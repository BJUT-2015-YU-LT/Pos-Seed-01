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
        this.list = "./data/data3_1.json";
        this.path = "./data/data3_2.json";
        this.cart = new Cart(list, path);
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
        Item item = new Item("电池", "个", 2.00, 1.0);
        int size = 2;
        Double result = size * 2.00;
        assertEquals(result, this.cart.print(size, item));
    }

}

