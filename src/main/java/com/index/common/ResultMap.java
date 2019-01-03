package com.index.common;

import java.io.Serializable;

/**
 * @Auther: Index
 * @Date: 2018/11/28 09:40
 * @Description:
 */
public class ResultMap<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String message;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}