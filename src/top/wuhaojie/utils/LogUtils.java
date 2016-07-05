package top.wuhaojie.utils;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/5 14:36
 * Version: 1.0
 */
public class LogUtils {
    public static void d(String tag, String msg) {
        System.out.println(tag + "[d]: \t" + msg);
    }

    public static void d(String msg) {
        System.out.println("[d]: \t" + msg);
    }

    public static void e(String tag, String msg) {
        System.out.println(tag + "[e]: \t" + msg);
    }

    public static void e(String msg) {
        System.out.println("[e]: \t" + msg);
    }
}
