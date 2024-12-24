package com.scan.devices.impl;

import com.scan.constants.Driver;
import com.scan.devices.Device;

public class WIADevice implements Device {
    public WIADevice(){
        super();
    }
    public WIADevice(String name){
        this.name=name;
    }
    private String name;
    @Override
    public String getName() {
        return name;
    }
    @Override
    public Driver getDriver() {
        return Driver.WIA;
    }
    @Override
    public void setName(String name) {
        this.name=name;
    }
}
