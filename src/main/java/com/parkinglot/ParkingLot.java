package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {
    private static int DEFAULT_CAPACITY = 10;
    private int currentCapacity;
    private final int totalCapacity;
    private List<Car> parkedCarList = new ArrayList<>();

    public ParkingLot() {
        this.totalCapacity = DEFAULT_CAPACITY;
        this.currentCapacity = totalCapacity;
    }

    public ParkingLot(int capacity) {
        this.totalCapacity = capacity;
        this.currentCapacity = totalCapacity;
    }

    public Ticket park(Car car) {
        if (currentCapacity == 0) {
            return null;
        }
        currentCapacity--;
        this.parkedCarList.add(car);
        return new Ticket(car.getId());
    }

    public Car fetch(Ticket ticket) {
        boolean haveParkedCar = parkedCarList.stream().filter(
                parkedCar -> parkedCar.getId() == ticket.getParkingLotId()
        ).findAny().isPresent();
        if (haveParkedCar) {
            this.parkedCarList = parkedCarList.stream().filter(
                    parkedCar -> parkedCar.getId() != ticket.getParkingLotId()
                    ).collect(Collectors.toList());
            currentCapacity++;
            return new Car(ticket.getParkingLotId());
        }
        return null;
    }
}
