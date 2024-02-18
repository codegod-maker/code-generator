package io.renren.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Result<T> {
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;

    @JsonIgnore
    public boolean isSuccess() {
        return code == 0;
    }

    public ResponseEntity<Result> toResponseEntity() {
        return toResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Result> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}
