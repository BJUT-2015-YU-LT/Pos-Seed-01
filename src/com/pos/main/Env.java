package com.pos.main;

import java.io.*;
import java.util.Properties;

/**
 * Created by pengzhendong on 16/1/9.
 */
public class Env {
    private static Properties prop = new Properties();

    public Env(){
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(".properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读属性对象prop读对应的健值
     * @param key
     * @return
     */
    public String getProperty(String key){
        return (String) this.prop.get(key);
    }
}
