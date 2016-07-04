package com.ivans.antrian.exception;

public class UnauthenticatedRequestException extends RuntimeException {

    public UnauthenticatedRequestException() {
    }

    public UnauthenticatedRequestException(String string) {
        super(string);
    }

    public UnauthenticatedRequestException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UnauthenticatedRequestException(Throwable thrwbl) {
        super(thrwbl);
    }

    public UnauthenticatedRequestException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
