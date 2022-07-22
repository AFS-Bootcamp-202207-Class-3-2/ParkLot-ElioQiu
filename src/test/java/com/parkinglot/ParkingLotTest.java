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
        Ticket ticket = parkingLot.park(new Car());
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_full_parkingLot_and_a_car() {
        //given
        ParkingLot fullParkingLot = new ParkingLot(1);
        fullParkingLot.park(new Car());
        //when
        Ticket ticket = fullParkingLot.park(new Car());
        //then
        assertNull(ticket);
    }

    
}
