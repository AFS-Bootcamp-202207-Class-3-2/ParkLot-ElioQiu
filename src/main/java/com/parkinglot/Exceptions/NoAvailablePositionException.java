package com.parkinglot.Exceptions;

public class NoAvailablePositionException extends RuntimeException{

    public NoAvailablePositionException() {
        super("No available position.");
    }
}
