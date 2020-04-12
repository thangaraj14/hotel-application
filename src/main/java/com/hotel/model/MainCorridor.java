package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

/**
 * All the lights in all the main corridors need to be switched ON
 */

@Getter
@Setter
public class MainCorridor extends Corridor {

    public MainCorridor(int id, Light light, AC ac) {
        this.id = id;
        this.light = light;
        this.ac = ac;
    }
}
