package com.scan.devices.impl;

import com.scan.constants.Driver;
import com.scan.devices.Device;

public class SANEDevice implements Device {

    public SANEDevice(){
        super();
    }
    public SANEDevice(String name){
        this.name=name;
    }
    private String name;
    @Override
    public String getName() {
        return name;
    }
    @Override
    public Driver getDriver() {
        return Driver.SANE;
    }
    @Override
    public void setName(String name) {
        this.name=name;
    }
}
