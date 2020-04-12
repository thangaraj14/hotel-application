package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ● Both main corridor and sub corridor have one light each
 * ● Both main and sub corridor have independently controllable ACs
 */
@Setter
@Getter
public class Corridor {
    int id;
    Light light;
    AC ac;

}
