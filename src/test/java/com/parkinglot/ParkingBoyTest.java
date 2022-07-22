package com.parkinglot;

import com.parkinglot.Constant.Constant;
import com.parkinglot.Exceptions.NoAvailablePositionException;
import com.parkinglot.Exceptions.UnrecognizedParkingTicketException;
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
    void should_return_parking_ticket_by_second_parkingLot_park_given_a_parking_boy_with_two_parkingLots_and_a_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(new Car(1));
        //when
        Ticket ticket = parkingBoy.park(new Car(2));
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
    void should_return_right_car_when_fetch_car_twice_given_a_parking_boy_with_two_parkingLots_and_two_parkedCars_and_two_tickets() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Ticket ticket1 = parkingBoy.park(new Car(1));
        Ticket ticket2 = parkingBoy.park(new Car(2));
        //when
        Car car1 = parkingBoy.fetch(ticket1);
        Car car2 = parkingBoy.fetch(ticket2);
        //then
        assertEquals(car1, new Car(1));
        assertEquals(car2, new Car(2));
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

    @Test
    void should_return_Unrecognized_parking_ticket_when_fetch_given_a_parking_boy_with_two_parkingLots_a_parkingLot_and_a_wrong_parking_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(new Car(1));
        parkingBoy.park(new Car(2));

        Ticket ticket = new Ticket(3);
        //when
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(ticket));
        //then
        assertEquals(Constant.UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_return_Unrecognized_parking_ticket_when_fetch_given_a_parking_boy_with_two_parkingLots_and_used_parking_ticket() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(new Car(1));
        parkingBoy.park(new Car(2));
        parkingBoy.fetch(new Ticket(1));
        Ticket ticket = new Ticket(1);
        //when
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class,
                () -> parkingBoy.fetch(ticket));
        //then
        assertEquals(Constant.UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }
}
