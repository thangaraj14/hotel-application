package com.hotel.demo;

import com.hotel.model.AC;
import com.hotel.model.Floor;
import com.hotel.model.Hotel;
import com.hotel.model.Light;
import com.hotel.model.MainCorridor;
import com.hotel.model.State;
import com.hotel.model.SubCorridor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * This application controls the power consumption by using sensor devices. It detects the movement and turns on the
 * light and off the AC. If there is no movement for a minutes it will turn on AC and turn off light.
 */
@SpringBootApplication
public class HotelApplication {

    private Hotel hotel;

    public static void main(String[] args) {
        HotelApplication application = new HotelApplication();
        application.initializeDefaultState(2, 1, 2);
        System.out.println("Default State :: ");
        application.printStatus();
        application.setMovement(1, 2);
        System.out.println("After Movement :: ");
        application.printStatus();
        application.resetMovement(1, 2);
        System.out.println("No Movement :: ");
        application.printStatus();
    }

    /**
     * It prints the status of hotel electronic equipments
     */
    private void printStatus() {
        for (Floor floor : hotel.getFloors().values()) {
            System.out.println("Floor " + floor.getId());
            for (MainCorridor mainCorridor : floor.getMainCorridors().values()) {
                System.out.println(
                        "Main corridor " + mainCorridor.getId() + " Light " + mainCorridor.getLight().getId() + " :  "
                                + mainCorridor.getLight().getState() + " AC" + " : " + mainCorridor.getAc().getState());
            }

            for (SubCorridor subCorridor : floor.getSubCorridors().values()) {
                System.out.println(
                        "Sub corridor " + subCorridor.getId() + " Light " + subCorridor.getLight().getId() + ": "
                                + subCorridor.getLight().getState() + " AC" + " : " + subCorridor.getAc().getState());
            }
        }
    }

    /**
     * When a motion is detected in one of the sub corridors the corresponding lights need to
     * be switched ON
     *
     * @param floor
     * @param subCorridor
     */
    public void setMovement(int floor, int subCorridor) {
        Floor floorObj = hotel.getFloors().get(floor);
        SubCorridor subCorridorObj = floorObj.getSubCorridors().get(subCorridor);
        subCorridorObj.getLight().on();

        for (Map.Entry<Integer, SubCorridor> map : floorObj.getSubCorridors().entrySet()) {
            if (map.getKey() != subCorridor && map.getValue().getAc().getState() == State.ON
                    && map.getValue().getLight().getState() == State.OFF) {
                map.getValue().getAc().off();
                subCorridorObj.setChange(map.getValue());
                break;
            }
        }
    }

    /**
     * When there is no motion for more than a minute the sub corridor lights should be
     * switched OFF and AC needs to be switched ON
     *
     * @param floor
     * @param subCorridor
     */
    public void resetMovement(int floor, int subCorridor) {
        Floor floorObj = hotel.getFloors().get(floor);
        SubCorridor subCorridorObj = floorObj.getSubCorridors().get(subCorridor);
        subCorridorObj.getLight().off();
        subCorridorObj.getChange().getAc().on();

    }

    /**
     * ● A Hotel can have multiple floors
     * ● Each floor can have multiple main corridors and sub corridors
     * ● Both main corridor and sub corridor have one light each
     *
     * @param noOfFloors
     * @param noOfMainCorridors
     * @param noOfSubCorridors
     *
     * @return
     */
    public Hotel initializeDefaultState(int noOfFloors, int noOfMainCorridors, int noOfSubCorridors) {

        HashMap<Integer, MainCorridor> mainCorridors = new HashMap<>();
        for (int j = 0; j < noOfMainCorridors; j++) {
            mainCorridors.put(j + 1, new MainCorridor(j + 1, new Light(j + 1, State.ON), new AC(State.ON)));
        }

        HashMap<Integer, SubCorridor> subCorridors = new HashMap<>();
        for (int k = 0; k < noOfSubCorridors; k++) {
            subCorridors.put(k + 1, new SubCorridor(k + 1, new Light(k + 1, State.OFF), new AC(State.ON)));
        }

        HashMap<Integer, Floor> floors = new HashMap<>();
        for (int i = 0; i < noOfFloors; i++) {
            floors.put(i + 1, new Floor(i + 1, mainCorridors, subCorridors));
        }

        this.hotel = new Hotel(floors);
        return this.hotel;
    }
}
