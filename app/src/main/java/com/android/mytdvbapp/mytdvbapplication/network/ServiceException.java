package com.android.mytdvbapp.mytdvbapplication.network;

/**
 * Created by antoinepelletier on 16/12/2017.
 */

public class ServiceException extends Exception {

    private Throwable throwable;
    private ServiceExceptionType type;
    private int code;

    public ServiceException(int code) {
        this.code = code;
    }

    public ServiceException(Throwable throwable, ServiceExceptionType type) {
        this.throwable = throwable;
        this.type = type;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ServiceExceptionType getType() {
        return type;
    }
}
