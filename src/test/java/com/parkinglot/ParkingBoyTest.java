package com.parkinglot;

import com.parkinglot.Constant.Constant;
import com.parkinglot.Exceptions.NoAvailablePositionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_a_car_when_fetch_given_a_parking_boy_with_two_parkingLots_and_a_parking_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(new Car(1));
        parkingBoy.park(new Car(2));
        Ticket ticket = new Ticket(2);
        //when
        Car car = parkingBoy.fetch(ticket);
        //then
        assertNotNull(car);
    }


    @Test
    void should_return_No_available_position_when_fetch_given_a_parking_boy_with_full_parkingLot_and_a_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(new Car(1));
        parkingBoy.park(new Car(2));
        //when
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> parkingBoy.park(new Car(3)));
        //then
        assertEquals(Constant.NO_AVAILABLE_POSITION, exception.getMessage());
    }

    
}
