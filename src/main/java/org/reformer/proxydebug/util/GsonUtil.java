package org.reformer.proxydebug.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Gson使用工具
 */
public class GsonUtil {

    private static Gson gson = new GsonBuilder().create();

    /**
     * bean to json
     * @param obj
     * @return
     */
    public static String bean2Json(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * json to bean
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return gson.fromJson(jsonStr, objClass);
    }

    public static String jsonFormatter(String uglyJsonStr) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(uglyJsonStr);
        return gson.toJson(je);
    }
}
