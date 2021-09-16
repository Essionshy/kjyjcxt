package com.tingyu.antimicrobial.monitor.exception;

/**
 * @Author essionshy
 * @CreatDate 2021/9/16 下午10:25
 * @Description
 */
public class ServiceException extends RuntimeException {

    private int status;
    private String message;

    public ServiceException(int status, String message) {
        super(message);
        this.status = status;
    }

    /*getter and setter*/
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
