package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class ReadFileTest {

    private ReadFile readFile;
    private String path;

    @Before
    public void setUp() throws Exception {
        readFile = new ReadFile();
        path = "./data/data1.json";
    }

    @Test
    public void testReadFile() throws Exception {
        String result = readFile.ReadFile(path);
        assertEquals("{\"firstName\":\"Brett\"}", result);
    }
}