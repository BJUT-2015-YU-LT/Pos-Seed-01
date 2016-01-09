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
        list = "./data/data3_1.json";
        path = "./data/data3_2.json";
        cart = new Cart(list, path);
    }

    /**
     * 测试返回的 Map
     * @throws Exception
     */
    @Test
    public void testReadIndex() throws Exception {
        Item cola = cart.getIndexList().get("ITEM000000");
        Item spirte = cart.getIndexList().get("ITEM000001");
        Item batterry = cart.getIndexList().get("ITEM000004");
        assertEquals("可口可乐", cola.getName());
        assertEquals("雪碧", spirte.getName());
        assertEquals("电池", batterry.getName());
    }

    /**
     * 测试打印所有商品价格
     * @throws Exception
     */
    @Test
    public void testPrintAll() throws Exception {
        Double result = 23.00;
        assertEquals(result, cart.printAll());
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
        assertEquals(result, cart.print(size, item));
    }

}

