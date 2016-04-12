/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.exception;

/**
 *
 * @author ivans
 */
public class AntrianServerException extends RuntimeException {

    public AntrianServerException() {
    }

    public AntrianServerException(String message) {
        super(message);
    }

    public AntrianServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AntrianServerException(Throwable cause) {
        super(cause);
    }

    public AntrianServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
