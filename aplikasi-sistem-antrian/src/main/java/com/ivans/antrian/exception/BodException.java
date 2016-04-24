package com.ivans.antrian.exception;

public class BodException extends Exception {

    public BodException() {
    }

    public BodException(String string) {
        super(string);
    }

    public BodException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public BodException(Throwable thrwbl) {
        super(thrwbl);
    }

    public BodException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
