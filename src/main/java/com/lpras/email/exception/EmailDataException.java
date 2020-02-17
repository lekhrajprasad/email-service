package com.lpras.email.exception;

import org.apache.logging.log4j.util.Strings;

public class EmailDataException extends RuntimeException {
    String msg = Strings.EMPTY;
    public EmailDataException(String s) {
        super(s);
        this.msg = s;
    }
    public EmailDataException(String s, Throwable couse) {
        super(s,couse);
        this.msg = s;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmailDataException{");
        sb.append(msg);
        sb.append('}');
        return sb.toString();
    }
}
