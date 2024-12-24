package com.naps2.results;

public class ScanResult {
    private final int exitCode;
    private final String output;

    public ScanResult(int exitCode, String output) {
        this.exitCode = exitCode;
        this.output = output;
    }

    public int getExitCode() { return exitCode; }
    public String getOutput() { return output; }
    public boolean isSuccess() { return exitCode == 0; }
}