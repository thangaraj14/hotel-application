package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Motion in sub-corridors is input to the controller, which needs to keep track and optimise the
 * power consumption.
 */

@Getter
@Setter
public class SubCorridor extends Corridor {

    SubCorridor change;

    public SubCorridor(int id, Light light, AC ac) {
        this.id = id;
        this.light = light;
        this.ac = ac;
    }
}
