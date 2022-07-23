package com.parkinglot;

import com.parkinglot.Strategy.SmartStrategy;
import com.parkinglot.Strategy.SuperSmartStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyTest {
    public ParkingBoy parkingBoy;

    @BeforeEach
    void before() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(3));
        parkingLotList.add(new ParkingLot(3));
        parkingBoy = new ParkingBoy(new SuperSmartStrategy(parkingLotList));
    }

    @Test
    void should_return_car_in_first_parkingLot_when_park_given_a_super_smart_parkingBoy_with_two_parkingLot_and_the_same_rate_positions_and_a_car() {
        // given
        // when
        Ticket ticket = parkingBoy.park(new Car(1));
        // then
        boolean isParkedCarInFirstParkingLot = parkingBoy.getParkingLotList().get(0).isCarInThisLot(ticket);
        assertEquals(isParkedCarInFirstParkingLot, true);
    }

    @Test
    void should_return_car_in_more_rate_parkingLot_when_park_given_a_super_smart_parkingBoy_with_two_parkingLot_and_second_more_rate_positions_and_a_car() {
        //given
        parkingBoy.park(new Car(1));
        //when
        Ticket ticket = parkingBoy.park(new Car(2));
        //then
        boolean isParkedCarInSecondParkingLot = parkingBoy.getParkingLotList().get(1).isCarInThisLot(ticket);
        assertEquals(isParkedCarInSecondParkingLot, true);
    }

    @Test
    void should_return_right_car_when_fetch_given_a_super_smart_parkingBoy_with_two_parkingLot_and_two_ticket() {
        //given
        Ticket ticket1 = parkingBoy.park(new Car(1));
        Ticket ticket2 = parkingBoy.park(new Car(2));
        //when
        Car car1 = parkingBoy.fetch(ticket1);
        Car car2 = parkingBoy.fetch(ticket2);
        //then
        assertEquals(car1, new Car(1));
        assertEquals(car2, new Car(2));
    }

}
