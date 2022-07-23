package com.parkinglot;

import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(ParkingLot::haveCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
        return parkedLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(parkingLot -> parkingLot.isCarInThisLot(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
        return parkedLot.fetch(ticket);
    }
}
