package com.pos.main;

/**
 * Created by pengzhendong on 16/1/13.
 */
public class User {
    private String name;
    private boolean isVip;
    private int points;

    public User () {
        this.setName(null);
        this.setIsVip(false);
        this.setPoints(0);
    }

    public User (String name, boolean isVip, int points) {
        this.setName(name);
        this.setIsVip(isVip);
        this.setPoints(points);
    }

    /**
     * 积分规则
     */
    public static User addPoints(User user, Double count) {
        Double points = 0.0;
        if (user.points >= 0 && user.points  <= 200) {
            points = Math.floor(count / 5) * 1;
            user.setPoints(user.points + points.intValue());
            return user;
        }
        if (user.points > 200 && user.points <= 500) {
            points = Math.floor(count / 5) * 3;
            user.setPoints(user.points + points.intValue());
            return user;
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
     * 设置 isVip
     * @param isVip
     */
    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    /**
     * 设置 points
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * 获取 name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取 isVip
     * @return
     */
    public boolean getIsVip() {
        return this.isVip;
    }

    /**
     * 获取 points
     * @return
     */
    public int getPoints() {
        return this.points;
    }

}
