package com.parkinglot.Strategy;

import com.parkinglot.Car;
import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;

import java.util.List;

public class StupidStrategy implements ParkingStrategy{

    public StupidStrategy() {
    }

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLotList) {
        ParkingLot parkedLot = parkingLotList.stream()
                .filter(ParkingLot::haveCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
        return parkedLot.park(car);
    }

}
