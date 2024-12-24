package com.naps2.devices;

import com.naps2.constants.Driver;

public interface Device {
    public String getName();
    public Driver getDriver();
    public void setName(String name);
}
