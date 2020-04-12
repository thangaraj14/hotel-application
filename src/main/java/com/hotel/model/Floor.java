package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Each floor can have multiple main corridors and sub corridors
 */

@Getter
@Setter
public class Floor {

    int id;
    HashMap<Integer, MainCorridor> mainCorridors;
    HashMap<Integer, SubCorridor> subCorridors;

    public Floor(int id, HashMap<Integer, MainCorridor> mainCorridors, HashMap<Integer, SubCorridor> subCorridors) {
        this.id = id;
        this.mainCorridors = mainCorridors;
        this.subCorridors = subCorridors;
    }
}
