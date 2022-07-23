package com.parkinglot.Strategy;

import com.parkinglot.Car;
import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;

import java.util.List;

public class StupidStrategy implements ParkingStrategy{
    private List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public StupidStrategy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(ParkingLot::haveCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
        return parkedLot.park(car);
    }

    @Override
    public Car fetch(Ticket ticket) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(parkingLot -> parkingLot.isCarInThisLot(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
        return parkedLot.fetch(ticket);
    }
}
