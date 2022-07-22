package com.parkinglot.Exceptions;

import com.parkinglot.Constant.Constant;

public class UnrecognizedParkingTicketException extends RuntimeException{
    public UnrecognizedParkingTicketException() {
        super(Constant.UNRECOGNIZED_PARKING_TICKET);
    }
}
