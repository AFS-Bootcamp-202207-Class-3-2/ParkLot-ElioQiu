package com.parkinglot;

import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(ParkingLot::haveCapacity)
                .max(Comparator.comparingInt(ParkingLot::getCurrentCapacity))
                .orElseThrow(NoAvailablePositionException::new);
        return parkingLot.park(car);
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
