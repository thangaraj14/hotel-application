package com.hotel.model;

/**
 * Both main and sub corridor ACs consume 10 units of power when ON
 * Both main and sub corridor lights consume 5 units of power when ON
 * <p>
 * Here we can add the basic features of the electronic equipments
 */
public interface ElectronicEquipment {

    void on();

    void off();

}
