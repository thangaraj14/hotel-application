package com.hotel.demo;

import com.hotel.model.Floor;
import com.hotel.model.Hotel;
import com.hotel.model.State;
import com.hotel.model.SubCorridor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class HotelApplicationTest {

    @InjectMocks
    HotelApplication application;

    @Test
    void testInitializeDefaultStatePositive() {
        Hotel hotel = application.initializeDefaultState(2, 1, 2);
        Assertions.assertEquals(2, hotel.getFloors().size());
        HashMap<Integer, Floor> floors = hotel.getFloors();
        for (Floor floor : floors.values()) {
            Assertions.assertEquals(1, floor.getMainCorridors().size());
            Assertions.assertEquals(2, floor.getSubCorridors().size());
        }
    }

    @Test
    void testInitializeDefaultStateNegative() {
        Hotel hotel = application.initializeDefaultState(2, 1, 2);
        Assertions.assertEquals(2, hotel.getFloors().size());
        HashMap<Integer, Floor> floors = hotel.getFloors();
        for (Floor floor : floors.values()) {
            Assertions.assertEquals(1, floor.getMainCorridors().size());
            Assertions.assertFalse(1 == floor.getSubCorridors().size());
        }
    }

    @Test
    public void testSetMovementPositive() {
        Hotel hotel = application.initializeDefaultState(2, 1, 2);
        application.setMovement(1, 2);

        HashMap<Integer, SubCorridor> subCorridor = hotel.getFloors().get(1).getSubCorridors();
        Assertions.assertTrue(subCorridor.get(2).getLight().getState() == State.ON);
        Assertions.assertTrue(subCorridor.get(1).getAc().getState() == State.OFF);
    }

    @Test
    public void testSetMovementNegative() {
        Hotel hotel = application.initializeDefaultState(2, 1, 2);
        application.setMovement(1, 2);

        HashMap<Integer, SubCorridor> subCorridor = hotel.getFloors().get(1).getSubCorridors();
        Assertions.assertFalse(subCorridor.get(2).getLight().getState() == State.OFF);
        Assertions.assertFalse(subCorridor.get(1).getAc().getState() == State.ON);
    }

    @Test
    public void testResetMovementPositive() {
        Hotel hotel = application.initializeDefaultState(2, 1, 2);
        application.setMovement(1, 2);
        application.resetMovement(1, 2);

        HashMap<Integer, SubCorridor> subCorridor = hotel.getFloors().get(1).getSubCorridors();
        Assertions.assertTrue(subCorridor.get(2).getLight().getState() == State.OFF);
        Assertions.assertTrue(subCorridor.get(1).getAc().getState() == State.ON);
    }

    @Test
    public void testResetMovementNegative() {
        Hotel hotel = application.initializeDefaultState(2, 1, 2);
        application.setMovement(1, 2);
        application.resetMovement(1, 2);

        HashMap<Integer, SubCorridor> subCorridor = hotel.getFloors().get(1).getSubCorridors();
        Assertions.assertFalse(subCorridor.get(2).getLight().getState() == State.ON);
        Assertions.assertFalse(subCorridor.get(1).getAc().getState() == State.OFF);
    }

}
