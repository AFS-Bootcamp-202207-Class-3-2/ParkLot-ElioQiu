package com.parkinglot;

import com.parkinglot.Strategy.ParkingStrategy;

import java.util.List;

public class ParkingBoy {
    private ParkingStrategy parkingStrategy;
    public ParkingBoy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingStrategy.fetch(ticket);
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingStrategy.getParkingLotList();
    }
}
