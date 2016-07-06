package top.wuhaojie.utils;

import com.google.gson.Gson;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2016/7/6 19:42
 * Version: 1.0
 */
public class GsonUtils {

    private static Gson mGson = new Gson();

    public static String toGson(Object object) {
        return mGson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return mGson.fromJson(json, tClass);
    }

}
