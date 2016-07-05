package top.wuhaojie.constant;

import java.io.*;
import java.util.Properties;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/5/6 12:13
 * Version: 1.0
 */
public class ConfigUtils {

    private static Properties properties;

    private static File file;

    static {
        // 读取配置文件
        properties = new Properties();
        file = new File(Constants.CONFIG_FILE_PATH);
        try {
            properties.load(new FileInputStream(file));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static String readConfig(String key) {
        return properties.getProperty(key, "");
    }

    public static boolean saveConfig(String key, String value) {
        try {
            properties.setProperty(key, value);
            properties.store(new FileOutputStream(file), "updated " + key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
