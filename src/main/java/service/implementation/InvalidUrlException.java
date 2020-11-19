package service.implementation;

public class InvalidUrlException extends RuntimeException {

    public InvalidUrlException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
