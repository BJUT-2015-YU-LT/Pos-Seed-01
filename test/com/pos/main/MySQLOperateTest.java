package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLOperateTest {

    /**
     * 测试导入商品信息
     * @throws Exception
     */
    @Test
    public void testReadItem() throws Exception {
         boolean result = MySQLOperate.readItem();
        assertEquals(true, result);
    }

    /**
     * 测试导入用户信息
     * @throws Exception
     */
    @Test
    public void testReadUser() throws Exception {
        boolean result = MySQLOperate.readUser();
        assertEquals(true, result);
    }

    /**
     * 测试构造的插入商品语句
     * @throws Exception
     */
    @Test
    public void testInsertItem() throws Exception {
        String barcode = "ITEMOOOOO4";
        Item item = new Item("电池", "个", 2.00, 1.0, true, 1.0);
        String sql = MySQLOperate.insertItem(barcode, item);
        assertEquals("INSERT INTO items VALUES ( \"ITEMOOOOO4\", \"电池\", \"个\", 2.0, 1.0, true, 1.0 );", sql);
    }

    /**
     * 测试构造的插入用户语句
     * @throws Exception
     */
    @Test
    public void testInsertUser() throws Exception {
        String id = "USER0001";
        User user = new User("USER 001", true, 10);
        String sql = MySQLOperate.insertUser(id, user);

        assertEquals("INSERT INTO users VALUES ( \"USER0001\", \"USER 001\", true, 10 );", sql);
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

        String sql = "SELECT COUNT(*) FROM items;";
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

    /**
     * 测试查询用户信息
     * @throws Exception
     */
    @Test
    public void testQueryUser() throws Exception {
        User user = MySQLOperate.queryUser("USER0001");
        assertNotNull(user);
    }

    /**
     * 测试从数据库获取商品信息
     * @throws Exception
     */
    @Test
    public void testQueryDataBase() throws Exception {
        String barcode = "ITEM0001";
        assertNotNull(MySQLOperate.queryDataBase(barcode));
    }
}