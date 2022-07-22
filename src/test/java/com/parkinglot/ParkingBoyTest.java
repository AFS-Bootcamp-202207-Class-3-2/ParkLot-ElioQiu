package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {
    
    @Test
    void should_return_parking_ticket_when_park_given_a_parking_boy_with_two_parkingLots_and_a_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        //when
        Ticket ticket = parkingBoy.park(new Car(1));
        //then
        assertNotNull(ticket);
    }
    
}
