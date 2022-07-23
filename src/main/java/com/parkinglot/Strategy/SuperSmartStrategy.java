package com.parkinglot.Strategy;

import com.parkinglot.Car;
import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;

import java.util.Comparator;
import java.util.List;

public class SuperSmartStrategy implements ParkingStrategy{
    private List<ParkingLot> parkingLotList;

    public SuperSmartStrategy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(ParkingLot::haveCapacity)
                .max(Comparator.comparingDouble(ParkingLot::calculateRate))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLot.park(car);
    }

    @Override
    public Car fetch(Ticket ticket) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(parkingLot -> parkingLot.isCarInThisLot(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
        return parkedLot.fetch(ticket);
    }

    @Override
    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }
}
