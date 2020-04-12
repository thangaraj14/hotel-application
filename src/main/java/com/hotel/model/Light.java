package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

/**
 * For Light, we are maintaining the state and consumption of power.
 */
@Setter
@Getter
public class Light implements ElectronicEquipment {

    int id;
    State state;
    final int UNITS = 5;

    public Light(int id, State state) {
        this.id = id;
        this.state = state;
    }

    @Override
    public void on() {
        this.state = State.ON;
    }

    @Override
    public void off() {
        this.state = State.OFF;
    }

}
