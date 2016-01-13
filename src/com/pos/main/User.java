package com.pos.main;

import net.sf.json.JSONObject;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.io.IOException;

/**
 * Created by pengzhendong on 16/1/13.
 */
public class User {
    private String name;
    private int points;
    private boolean isVip;

    public User () {
        this.setName(null);
        this.setPoints(0);
        this.setIsVip(false);
    }

    public User (String name, int points, boolean isVip) {
        this.setName(name);
        this.setPoints(points);
        this.setIsVip(isVip);
    }

    /**
     * 根据会员姓名从文件读取会员信息
     * @param name
     * @return
     */
    public static User readUser(String name) {
        String result = "";
        try {
            result = ReadFile.ReadFile("./data/User.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = JSONObject.fromObject(result);
        JSONObject info = JSONObject.fromObject(obj.getString(name));
        User user = new User(name, info.getInt("points"), info.getBoolean("isVip"));

        return user;
    }

    /**
     * 加上获得的积分
     */
    public static User addPoints(User user, Double count) {
        Double points = 0.0;
        if (user.points >= 0 && user.points  < 200) {
            points = Math.floor(count / 5) * 1;
            user.setPoints(user.points + points.intValue());
        }
        if (user.points > 200 && user.points <= 500) {
            points = Math.floor(count / 5) * 3;
            user.setPoints(user.points + points.intValue());
        }
        if (user.points >= 500) {
            points = Math.floor(count / 5) * 5;
            user.setPoints(user.points+ points.intValue());
        }
        return user;
    }

    /**
     * 设置 name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置 points
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * 设置 isVip
     * @param isVip
     */
    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    /**
     * 获取 name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取 points
     * @return
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * 获取 isVip
     * @return
     */
    public boolean getIsVip() {
        return this.isVip;
    }
}
