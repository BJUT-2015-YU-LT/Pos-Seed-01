package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class MySQLTest {

    private MySQL mySQL;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        this.mySQL = new MySQL();
    }

    /**
     * 测试连接
     * @throws Exception
     */
    @Test
    public void testConnect() throws Exception {
        this.conn = mySQL.conn;
        assertNotNull(this.conn);
    }

}