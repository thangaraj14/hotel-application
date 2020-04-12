package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * A Hotel can have multiple floors
 */
@Setter
@Getter
public class Hotel {

    HashMap<Integer, Floor> floors;

    public Hotel(HashMap<Integer, Floor> floorHashMap) {
        this.floors = floorHashMap;
    }

}
