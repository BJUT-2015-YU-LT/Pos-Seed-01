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
        this.readFile = new ReadFile();
        this.path = "./data/data0.json";
    }

    /**
     * 测试读取的内容是否正确
     * @throws Exception
     */
    @Test
    public void testReadFile() throws Exception {
        String result = this.readFile.ReadFile(this.path);
        assertEquals("{\"firstName\":\"Brett\"}", result);
    }
}