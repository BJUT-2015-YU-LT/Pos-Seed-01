package com.pos.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class ReadFile {

    public String ReadFile(String path) throws IOException {

        File file = new File(path);

        if(!file.exists()||file.isDirectory()) {
            throw new FileNotFoundException();
        }

        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[1];
        StringBuffer sb = new StringBuffer();

        while((fis.read(buf)) != -1) {
            sb.append(new String(buf));
            buf=new byte[1];
        }

        return sb.toString();
    }
}
