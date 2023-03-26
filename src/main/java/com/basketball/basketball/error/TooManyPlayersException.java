package com.basketball.basketball.error;

public class TooManyPlayersException extends RuntimeException {
    public TooManyPlayersException(String message) {
        super(message);
    }
}
