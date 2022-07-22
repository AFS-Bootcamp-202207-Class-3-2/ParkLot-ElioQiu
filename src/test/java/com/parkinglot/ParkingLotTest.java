package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
    
    @Test
    void should_return_a_parking_ticket_when_park_given_a_parkingLot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        //when
        Ticket ticket = parkingLot.park(new Car(1));
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_full_parkingLot_and_a_car() {
        //given
        ParkingLot fullParkingLot = new ParkingLot(1);
        fullParkingLot.park(new Car(1 ));
        //when
        Ticket ticket = fullParkingLot.park(new Car(2));
        //then
        assertNull(ticket);
    }

    @Test
    void should_return_a_car_when_fetch_given_a_parkingLot_with_parked_car_and_a_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car(1));
        Ticket ticket = new Ticket(1);
        //when
        Car car = parkingLot.fetch(ticket);
        //then
        assertNotNull(car);
    }

    
}
