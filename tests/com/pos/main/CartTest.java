package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class CartTest {
    private Cart a ;
    @Before
    public void setUp() throws Exception {
        a =new Cart("./data/data1.json");

    }

    @Test
    public void testDealJson() throws Exception {

    }
}