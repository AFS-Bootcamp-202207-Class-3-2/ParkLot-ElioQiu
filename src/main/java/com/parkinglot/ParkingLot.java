package com.parkinglot;

public class ParkingLot {
    private static int DEFAULT_CAPACITY = 10;
    private int capacity;

    public ParkingLot() {
        capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (capacity == 0) {
            return null;
        }
        capacity--;
        return new Ticket();
    }
}
