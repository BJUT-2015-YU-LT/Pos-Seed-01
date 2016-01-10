package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLOperateTest {

    /**
     * 测试构造的插入语句
     * @throws Exception
     */
    @Test
    public void testSetInsert() throws Exception {
        String barcode = "ITEMOOOOO4";
        Item item = new Item("电池", "个", 2.00, 1.0, true);
        String sql = MySQLOperate.setInsert(barcode, item);
        assertEquals("INSERT INTO items VALUES ( \"ITEMOOOOO4\", \"电池\", \"个\", 2.0, 1.0, true );", sql);
    }

    /**
     * 测试插入数据后的列数
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        Connection conn = new Connect().conn;
        Statement smt = conn.createStatement();
        ResultSet rs = null;

        String sql = "select count(*) from items;";
        rs = smt.executeQuery(sql);
        int count = 0;
        while(rs.next())
        {
            count = rs.getInt(1);
        }
        rs.close();
        conn.close();
        assertEquals(3, count);
    }
}