package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class EnvTest {

    /**
     * 测试读取配置文件
     * @throws Exception
     */
    @Test
    public void testGetProperty() throws Exception {
        Env env =  new Env();
        String password = env.getProperty("PASSWORD");
        assertEquals("secret", password);
    }
}