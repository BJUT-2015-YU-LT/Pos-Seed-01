package com.pos.main;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

        Connection conn = new Connect().conn;
        Statement smt = conn.createStatement();
        ResultSet rs = null;

        String sql = "select count(*) from information_schema.`COLUMNS` "
                + "where TABLE_SCHEMA = 'pos' and TABLE_NAME = 'items';";
        rs = smt.executeQuery(sql);
        int count = 0;
        while(rs.next())
        {
            count = rs.getInt(1);
        }
        rs.close();
        assertEquals(7, count);

        sql = "select count(*) from information_schema.`COLUMNS` "
                + "where TABLE_SCHEMA = 'pos' and TABLE_NAME = 'logs';";
        rs = smt.executeQuery(sql);
        while(rs.next())
        {
            count = rs.getInt(1);
        }
        rs.close();
        assertEquals(8, count);

        sql = "select count(*) from information_schema.`COLUMNS` "
                + "where TABLE_SCHEMA = 'pos' and TABLE_NAME = 'users';";
        rs = smt.executeQuery(sql);
        while(rs.next())
        {
            count = rs.getInt(1);
        }
        rs.close();
        conn.close();
        assertEquals(4, count);
    }
}