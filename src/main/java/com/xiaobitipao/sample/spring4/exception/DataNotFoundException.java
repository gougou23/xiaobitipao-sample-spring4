package com.xiaobitipao.sample.spring4.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String url;

    public DataNotFoundException(String message, String url) {
        super(message);
        this.url = url;
    }

    public DataNotFoundException(String message, Throwable cause, String url) {
        super(message, cause);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
