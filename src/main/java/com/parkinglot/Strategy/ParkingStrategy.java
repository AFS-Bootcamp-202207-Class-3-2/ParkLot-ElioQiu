package com.parkinglot.Strategy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;

import java.util.List;

public interface ParkingStrategy {
    Ticket park(Car car);
    List<ParkingLot> getParkingLotList();
}
