package com.scan.devices;

import com.scan.constants.Driver;

public interface Device {
    public String getName();
    public Driver getDriver();
    public void setName(String name);
}
