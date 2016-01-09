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
        Double price = 3.00;
        Double discount = 0.8;
        boolean promotion = true;
        Item item = new Item("可口可乐", "瓶", price, discount, promotion);

        assertEquals("可口可乐", item.getName());
        assertEquals("瓶", item.getUnit());
        assertEquals(price, item.getPrice());
        assertEquals(discount, item.getDiscount());
        assertEquals(promotion, item.getPromotion());
    }

    /**
     * 测试 set 方法
     * @throws Exception
     */
    @Test
    public void testSetMethod() throws Exception {
        Double price = 3.00;
        Double discount = 0.8;
        boolean promotion = true;

        Item item = new Item();
        item.setName("可口可乐");
        item.setUnit("瓶");
        item.setPrice(price);
        item.setDiscount(discount);
        item.setPromotion(promotion);

        assertEquals("可口可乐", item.getName());
        assertEquals("瓶", item.getUnit());
        assertEquals(price, item.getPrice());
        assertEquals(discount, item.getDiscount());
        assertEquals(promotion, item.getPromotion());
    }
}