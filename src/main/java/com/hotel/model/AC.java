package com.hotel.model;

import lombok.Getter;
import lombok.Setter;

/**
 * For AC, we are maintaining the state and consumption of power.
 */
@Getter
@Setter
public class AC implements ElectronicEquipment {

    int id;
    State state;
    final int UNITS = 10;

    public AC(State state) {
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
