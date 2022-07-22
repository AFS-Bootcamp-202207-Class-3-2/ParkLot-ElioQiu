package com.parkinglot.Exceptions;

import com.parkinglot.Constant.Constant;

public class NoAvailablePositionException extends RuntimeException{

    public NoAvailablePositionException() {
        super(Constant.NO_AVAILABLE_POSITION);
    }
}
