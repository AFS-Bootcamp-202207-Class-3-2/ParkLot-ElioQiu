package com.parkinglot;

import com.parkinglot.Exceptions.NoAvailablePositionException;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) {
        List<ParkingLot> parkedLot = parkingLotList.stream().filter(parkingLot -> parkingLot.haveCapacity())
                .collect(Collectors.toList());
        if (parkedLot.size() != 0) {
            return parkedLot.get(0).park(car);
        }
        throw new NoAvailablePositionException();
    }
}
