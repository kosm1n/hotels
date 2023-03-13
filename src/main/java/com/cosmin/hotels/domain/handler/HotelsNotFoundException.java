package com.cosmin.hotels.domain.handler;


public class HotelsNotFoundException extends RuntimeException {

    public HotelsNotFoundException(String message) {
        super(message);
    }

    public HotelsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
