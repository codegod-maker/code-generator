package io.renren.utils;

import java.util.HashMap;

public class R1 extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private static final int ERROR_CODE = 500;

    public R1() {
        put("code", 200);
        put("msg", "success");
    }

    public static R1 error() {
        return error(ERROR_CODE, "未知异常，请联系管理员");
    }

    public static R1 error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static R1 error(int code, String msg) {
        R1 r = new R1();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R1 ok() {
        return new R1();
    }

    public static R1 ok(Object data) {
        R1 r = new R1();
        r.put("data",data);
        return r;
    }
}