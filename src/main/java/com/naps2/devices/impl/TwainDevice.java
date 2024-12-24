package com.naps2.devices.impl;

import com.naps2.constants.Driver;
import com.naps2.devices.Device;

public class TwainDevice implements Device {

    public TwainDevice(){
        super();
    }
    public TwainDevice(String name){
        this.name=name;
    }
    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Driver getDriver() {
        return Driver.TWAIN;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }
}
