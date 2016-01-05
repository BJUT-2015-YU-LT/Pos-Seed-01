package com.pos.main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/5.
 */
public class MainClassTest {

    private MainClass mainClass;

    @org.junit.Before
    public void setUp() throws Exception {
        mainClass = new MainClass();
    }

    @org.junit.Test
    public void testMain() throws Exception {

        assertEquals("helloworld", mainClass.sayHello());
    }

}