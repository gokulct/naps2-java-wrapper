package com.scan.devices.impl;

import com.scan.constants.Driver;
import com.scan.devices.Device;

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
