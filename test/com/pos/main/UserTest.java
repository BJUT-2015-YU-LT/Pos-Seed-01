package com.pos.main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/13.
 */
public class UserTest {

    /**
     * 测试从文件中读取用户信息
     * @throws Exception
     */
    @Test
    public void testReadUser() throws Exception {
        String name = "USER 001";
        boolean isVip = true;
        User user = User.readUser(name);

        assertEquals(name, user.getName());
        assertEquals(isVip, user.getIsVip());
    }

    /**
     * 测试添加用户积分
     * @throws Exception
     */
    @Test
    public void testAddPoints() throws Exception {
        User user1 = new User("USER 001", 10, true);
        User user2 = new User("USER 001", 201, true);
        User user3 = new User("USER 001", 501, true);

        user1 = User.addPoints(user1, 10.0);
        user2 = User.addPoints(user2, 10.0);
        user3 = User.addPoints(user3, 10.0);

        assertEquals(12, user1.getPoints());
        assertEquals(207, user2.getPoints());
        assertEquals(511, user3.getPoints());
    }

    /**
     * 测试 get 方法
     * @throws Exception
     */
    @Test
    public void testSetMethod() throws Exception {
        String name = "USER 003";
        int points = 5;
        boolean isVip = true;
        User user = new User(name, points, isVip);

        assertEquals(name, user.getName());
        assertEquals(points, user.getPoints());
        assertEquals(isVip, user.getIsVip());
    }

    /**
     * 测试 set 方法
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        String name = "USER 003";
        int points = 5;
        boolean isVip = true;
        User user = new User();
        user.setName(name);
        user.setPoints(points);
        user.setIsVip(isVip);

        assertEquals(name, user.getName());
        assertEquals(points, user.getPoints());
        assertEquals(isVip, user.getIsVip());
    }
}