package com.parkinglot;

import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;

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

    public Car fetch(Ticket ticket) {
        List<ParkingLot> parkedLot = parkingLotList.stream().filter(parkingLot -> parkingLot.isCarInThisLot(ticket))
                .collect(Collectors.toList());
        if (parkedLot.size() != 0) {
            return parkedLot.get(0).fetch(ticket);
        }
        throw new UnrecognizedParkingTicketException();
    }
}
