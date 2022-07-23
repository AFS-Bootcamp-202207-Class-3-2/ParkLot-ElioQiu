package com.parkinglot;

import com.parkinglot.Constant.Constant;
import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    @Test
    void should_return_car_in_first_parkingLot_when_park_given_a_smart_parkingBoy_with_two_parkingLot_and_the_same_empty_positions_and_a_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        //when
        Ticket ticket = smartParkingBoy.park(new Car(1));
        //then
        boolean isParkedCarInFirstParkingLot = smartParkingBoy.getParkingLotList().get(0).isCarInThisLot(ticket);
        assertEquals(isParkedCarInFirstParkingLot, true);
    }

    @Test
    void should_return_car_in_more_empty_parkingLot_when_park_given_a_smart_parkingBoy_with_two_parkingLot_and_second_more_empty_positions_and_a_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        //when
        Ticket ticket = smartParkingBoy.park(new Car(1));
        //then
        boolean isParkedCarInSecondParkingLot = smartParkingBoy.getParkingLotList().get(1).isCarInThisLot(ticket);
        assertEquals(isParkedCarInSecondParkingLot, true);
    }

    @Test
    void should_return_right_car_when_fetch_given_a_smart_parkingBoy_with_two_parkingLot_and_two_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Ticket ticket1 = smartParkingBoy.park(new Car(1));
        Ticket ticket2 = smartParkingBoy.park(new Car(2));
        //when
        Car car1 = smartParkingBoy.fetch(ticket1);
        Car car2 = smartParkingBoy.fetch(ticket2);
        //then
        assertEquals(car1, new Car(1));
        assertEquals(car2, new Car(2));
    }

    @Test
    void should_return_No_available_position_when_fetch_given_a_smart_parking_boy_with_full_parkingLot_and_a_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        smartParkingBoy.park(new Car(1));
        smartParkingBoy.park(new Car(2));
        //when
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> smartParkingBoy.park(new Car(3)));
        //then
        assertEquals(Constant.NO_AVAILABLE_POSITION, exception.getMessage());
    }

    @Test
    void should_return_Unrecognized_parking_ticket_when_fetch_given_a_smart_parking_boy_with_two_parkingLots_a_parkingLot_and_a_wrong_parking_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        smartParkingBoy.park(new Car(1));
        smartParkingBoy.park(new Car(2));

        Ticket ticket = new Ticket(3);
        //when
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> smartParkingBoy.fetch(ticket));
        //then
        assertEquals(Constant.UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_return_Unrecognized_parking_ticket_when_fetch_given_a_smart_parking_boy_with_two_parkingLots_and_used_parking_ticket() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        smartParkingBoy.park(new Car(1));
        smartParkingBoy.park(new Car(2));
        smartParkingBoy.fetch(new Ticket(1));
        Ticket ticket = new Ticket(1);
        //when
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> smartParkingBoy.fetch(ticket));
        //then
        assertEquals(Constant.UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

}
