package com.parkinglot;

public class Ticket {
    private int parkingLotId;

    public int getParkingLotId() {
        return parkingLotId;
    }

    public Ticket(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
