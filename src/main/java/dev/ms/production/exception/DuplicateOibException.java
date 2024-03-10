package dev.ms.production.exception;

public class DuplicateOibException  extends Exception{

    public DuplicateOibException() {
    }

    public DuplicateOibException(String message) {
        super(message);
    }

    public DuplicateOibException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateOibException(Throwable cause) {
        super(cause);
    }

    public DuplicateOibException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
