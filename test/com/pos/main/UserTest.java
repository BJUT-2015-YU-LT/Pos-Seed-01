package com.pos.main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pengzhendong on 16/1/13.
 */
public class UserTest {

    /**
     * 测试添加用户积分,边界值测试
     * @throws Exception
     */
    @Test
    public void testAddPoints() throws Exception {
        User user1 = new User("USER 001", true, 0);
        User user2 = new User("USER 001", true, 101);
        User user3 = new User("USER 001", true, 200);
        User user4 = new User("USER 001", true, 201);
        User user5 = new User("USER 001", true, 303);
        User user6 = new User("USER 001", true, 500);
        User user7 = new User("USER 001", true, 555);

        user1 = User.addPoints(user1, 11.0);
        user2 = User.addPoints(user2, 10.0);
        user3 = User.addPoints(user3, 17.0);
        user4 = User.addPoints(user4, 22.0);
        user5 = User.addPoints(user5, 44.0);
        user6 = User.addPoints(user6, 55.0);
        user7 = User.addPoints(user7, 66.0);

        assertEquals(2, user1.getPoints());
        assertEquals(103, user2.getPoints());
        assertEquals(203, user3.getPoints());
        assertEquals(213, user4.getPoints());
        assertEquals(327, user5.getPoints());
        assertEquals(533, user6.getPoints());
        assertEquals(620, user7.getPoints());
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
        User user = new User(name, isVip, points);

        assertEquals(name, user.getName());
        assertEquals(isVip, user.getIsVip());
        assertEquals(points, user.getPoints());
    }

    /**
     * 测试 set 方法
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        String name = "USER 003";
        boolean isVip = true;
        int points = 5;
        User user = new User();
        user.setName(name);
        user.setIsVip(isVip);
        user.setPoints(points);

        assertEquals(name, user.getName());
        assertEquals(isVip, user.getIsVip());
        assertEquals(points, user.getPoints());
    }
}