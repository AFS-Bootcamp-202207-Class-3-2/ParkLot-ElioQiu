package com.parkinglot;

import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;
import com.parkinglot.Strategy.ParkingStrategy;

import java.util.List;

public class ParkingBoy {
    private ParkingStrategy parkingStrategy;
    private List<ParkingLot> parkingLotList;
    public ParkingBoy(ParkingStrategy parkingStrategy, List<ParkingLot> parkingLotList) {
        this.parkingStrategy = parkingStrategy;
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(car, parkingLotList);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(parkingLot -> parkingLot.isCarInThisLot(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
        return parkedLot.fetch(ticket);
    }

    public List<ParkingLot> getParkingLotList() {
        return this.parkingLotList;
    }
}
