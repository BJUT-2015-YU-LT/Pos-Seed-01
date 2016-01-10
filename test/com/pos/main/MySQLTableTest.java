package com.pos.main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLTableTest {

    /**
     * 测试数据库建表
     * @throws Exception
     */
    @Test
    public void testOnUpgrade() throws Exception {
        boolean result = MySQLTable.onUpgrade();
        assertEquals(true, result);
    }
}