package com.parkinglot;

import com.parkinglot.Exceptions.NoAvailablePositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void should_return_return_No_available_position_when_park_given_full_parkingLot_and_a_car() {
        //given
        ParkingLot fullParkingLot = new ParkingLot(1);
        fullParkingLot.park(new Car(1 ));
        //when
        Ticket ticket = fullParkingLot.park(new Car(2));
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class,
                () -> fullParkingLot.park(new Car(2)));
        //then
        assertEquals("No available position.", exception.getMessage());
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

    @Test
    void should_return_right_car_when_fetch_car_twice_given_two_parkedCars_and_two_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot(2);
        Ticket ticket1 = parkingLot.park(new Car(1));
        Ticket ticket2 = parkingLot.park(new Car(2));
        //when
        Car car1 = parkingLot.fetch(ticket1);
        Car car2 = parkingLot.fetch(ticket2);
        //then
        assertEquals(car1, new Car(1));
        assertEquals(car2, new Car(2));
    }

    @Test
    void should_return_null_when_fetch_given_a_parkingLot_and_a_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car(1));
        Ticket ticket = new Ticket(2);
        //when
        Car car = parkingLot.fetch(ticket);
        //then
        assertNull(car);
    }

    @Test
    void should_return_null_when_fetch_given_a_parkingLot_and_a_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car(1));
        parkingLot.fetch(new Ticket(1));
        Ticket ticket = new Ticket(1);
        //when
        Car car = parkingLot.fetch(ticket);
        //then
        assertNull(car);
    }
}
