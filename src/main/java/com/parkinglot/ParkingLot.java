package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {
    private static int DEFAULT_CAPACITY = 10;
    private int capacity;
    private List<Car> parkedCarList = new ArrayList<>();

    public ParkingLot() {
        capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (capacity == 0) {
            return null;
        }
        capacity--;
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
            return new Car(ticket.getParkingLotId());
        }
        return null;
    }
}
