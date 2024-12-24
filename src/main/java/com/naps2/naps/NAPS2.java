package com.naps2.naps;

import com.naps2.constants.Driver;
import com.naps2.devices.Device;
import com.naps2.devices.impl.SANEDevice;
import com.naps2.devices.impl.TwainDevice;
import com.naps2.devices.impl.WIADevice;
import com.naps2.exceptions.ScanExceptions;
import com.naps2.results.ScanResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NAPS2 {
    private final String executablePath;
    private final ProcessBuilder processBuilder;

    public NAPS2(String naps2ConsolePath) {
        this.executablePath = naps2ConsolePath;
        this.processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
    }

    public NAPS2(){
        this.executablePath=NAPS2.class.getResource("/App/NAPS2.Console.exe").getPath();
        this.processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
    }

    public ScanResult scan(String outputPath) throws IOException,ScanExceptions {
        return executeCommand("-o", outputPath);
    }

    public ScanResult scanWithProfile(String profileName, String outputPath) throws IOException,ScanExceptions {
        return executeCommand("-o", outputPath ,"--profile", profileName);
    }

    public ScanResult autoScan(String outputPath) throws IOException,ScanExceptions {
        return executeCommand("autoscan", outputPath);
    }

    public List<Device> listDevices(Driver driver) throws IOException,ScanExceptions {
        ScanResult result=null;
        List<Device> devices = new ArrayList<Device>();
        switch (driver){
            case TWAIN -> {
                result = executeCommand("--listdevices","--driver","twain");
                String[] arr = result.getOutput().split("\n");
                Arrays.stream(arr).filter((a)->!a.toLowerCase().contains("qt:")).forEach((i)->devices.add(new TwainDevice(i)));
            }
            case WIA -> {
                result = executeCommand("--listdevices","--driver","wia");
                String[] arr = result.getOutput().split("\n");
                Arrays.stream(arr).filter((a)->!a.toLowerCase().contains("qt:")).forEach((i)->devices.add(new WIADevice(i)));
            }
            case SANE -> {
                result = executeCommand("--listdevices","--driver","sane");
                String[] arr = result.getOutput().split("\n");
                Arrays.stream(arr).filter((a)->!a.toLowerCase().contains("qt:")).forEach((i)->devices.add(new SANEDevice(i)));
            }
            case ESCL -> {
                result = executeCommand("--listdevices","--driver","escl");
                String[] arr = result.getOutput().split("\n");
                Arrays.stream(arr).filter((a)->!a.toLowerCase().contains("qt:")).forEach((i)->devices.add(new SANEDevice(i)));
            }
            case APPLE -> {
                result = executeCommand("--listdevices","--driver","apple");
                String[] arr = result.getOutput().split("\n");
                Arrays.stream(arr).filter((a)->!a.toLowerCase().contains("qt:")).forEach((i)->devices.add(new SANEDevice(i)));
            }
            default ->{
                return null;
            }
        }
        return devices;
    }

    public ScanResult executeCommand(String... args) throws IOException, ScanExceptions {
        List<String> command = new ArrayList<>();
        command.add(executablePath);
        command.addAll(Arrays.asList(args));
        command = command.stream().map((s)->s.toString().replaceAll("\r","")).toList();
        processBuilder.command(command);
        Process process = processBuilder.start();
        String output = new String(process.getInputStream().readAllBytes());
        if(output.contains("No devices") || output.contains("ERROR(S):") || output.contains("File already exists")){
            throw new ScanExceptions(output);
        }
        try {
            int exitCode = process.waitFor();
            return new ScanResult(exitCode, output);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Process interrupted", e);
        }
    }
    public ScanBuilder createScanBuilder() {
        return new ScanBuilder(this);
    }
}