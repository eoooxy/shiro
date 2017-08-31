package com.blogx.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-08-29 下午2:54
 */
public class ResUtils implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public static String ok() {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", "操作成功");
        return JSON.toJSONString(map);
    }

    public static String ok(Object o) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", "操作成功");
        map.put("data", o);
        return JSON.toJSONString(map);
    }

    public static String ok(String msg) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }

    public static String err() {
        Map map = new HashMap();
        map.put("code", 200);
        map.put("msg", "操作失败");
        return JSON.toJSONString(map);
    }

    public static String err(String msg) {
        Map map = new HashMap();
        map.put("code", 200);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }

    public static String err(Object o) {
        Map map = new HashMap();
        map.put("code", 200);
        map.put("msg", "操作失败");
        map.put("data", o);
        return JSON.toJSONString(map);
    }

    public static String other(String code, String msg) {
        return other(code, msg, null);
    }

    public static String other(String code, String msg, Object o) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("msg", msg);
        if (o != null) {
            map.put("data", o);
        }
        return JSON.toJSONString(map);
    }


}
