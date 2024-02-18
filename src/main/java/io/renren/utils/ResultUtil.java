package io.renren.utils;



public class ResultUtil {

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(1);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(Result origin) {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMsg(origin.getMsg());
        return result;
    }

}
