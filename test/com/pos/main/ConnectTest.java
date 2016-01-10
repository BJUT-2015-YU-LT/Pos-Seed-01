package com.pos.main;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/10.
 */
public class ConnectTest {

    private Connect connect;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        this.connect = new Connect();
    }

    /**
     * 测试连接
     * @throws Exception
     */
    @Test
    public void testConnect() throws Exception {
        this.conn = connect.conn;
        assertEquals(false, conn.isClosed());
    }

}