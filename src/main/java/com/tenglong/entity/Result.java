package com.tenglong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Result<T> {
    private boolean flag;

    private Integer code;

    private String message;

    private T data;
    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(){}
}
