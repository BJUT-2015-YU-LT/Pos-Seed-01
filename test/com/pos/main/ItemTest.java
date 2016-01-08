package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/8.
 */
public class ItemTest {

    /**
     * 测试 get 方法
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        Double price = 3.0;
        Double discount = 0.8;
        Item item = new Item("ITEM000000", "可口可乐", "瓶", price, discount);
        assertEquals("ITEM000000", item.getBarcode());
        assertEquals("可口可乐", item.getName());
        assertEquals("瓶", item.getUnit());
        assertEquals(price, item.getPrice());
        assertEquals(discount, item.getDiscount());
    }

    /**
     * 测试 set 方法
     * @throws Exception
     */
    @Test
    public void testSetMethod() throws Exception {
        Double price = 3.0;
        Double discount = 0.8;
        Item item = new Item();
        item.setBarcode("ITEM000000");
        item.setName("可口可乐");
        item.setUnit("瓶");
        item.setPrice(price);
        item.setDiscount(discount);
        assertEquals("ITEM000000", item.getBarcode());
        assertEquals("可口可乐", item.getName());
        assertEquals("瓶", item.getUnit());
        assertEquals(price, item.getPrice());
        assertEquals(discount, item.getDiscount());
    }
}