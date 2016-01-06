package com.pos.main;

import java.io.*;

/**
 * Created by pengzhendong on 16/1/6.
 */
public class ReadFile {

    /**
     *
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String ReadFile(String path) throws IOException {

        File file = new File(path);

        if(!file.exists()||file.isDirectory()) {
            throw new FileNotFoundException();
        }

        StringBuffer sb = new StringBuffer();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
