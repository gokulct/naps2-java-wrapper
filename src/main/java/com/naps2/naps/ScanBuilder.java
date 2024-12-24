package com.naps2.naps;

import com.naps2.constants.Mode;
import com.naps2.constants.PDFCompact;
import com.naps2.constants.PageSize;
import com.naps2.constants.Source;
import com.naps2.devices.Device;
import com.naps2.exceptions.ScanExceptions;
import com.naps2.pdf.PDFOptions;
import com.naps2.results.ScanResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScanBuilder {
    private final NAPS2 wrapper;
    private final List<String> arguments = new ArrayList<>();

    public ScanBuilder(NAPS2 wrapper) {
        this.wrapper = wrapper;
    }

    public ScanBuilder withDevice(Device device) {
        //arguments.add("-p");
        arguments.add("--noprofile");
        arguments.add("--driver");
        arguments.add(device.getDriver().name().toLowerCase());
        arguments.add("--device");
        arguments.add("\""+device.getName()+"\"");
        return this;
    }

    public ScanBuilder withPageSize(PageSize pageSize){
        arguments.add(pageSize.name().toLowerCase());
        return this;
    }

    public ScanBuilder withProfile(String profile){
        if(profile!=null && !profile.isEmpty()){
            arguments.add("-p");
            arguments.add(profile);
        }
        return this;
    }

    public ScanBuilder withDpi(int dpi) {
        arguments.add("--dpi");
        arguments.add(String.valueOf(dpi));
        return this;
    }

    public ScanBuilder withMode(Mode mode) { // e.g., "color", "bw", "gray"
        arguments.add("--bitdepth");
        arguments.add(mode.name().toLowerCase());
        return this;
    }

    public ScanBuilder withSource(Source source){
        arguments.add("--source");
        arguments.add(source.name());
        return this;
    }

    public ScanBuilder forceFileWrite(boolean enable){
        if(enable){
            arguments.add("-f");
        }
        return this;
    }
    public ScanBuilder withPDFOptions(PDFOptions options){
        if(options.getName()!=null && !options.getName().isEmpty()){
            arguments.add("--pdftitle");
            arguments.add("\""+options.getName()+"\"");
        }
        if(options.getAuthor()!=null && !options.getAuthor().isEmpty()){
            arguments.add("--pdfauthor");
            arguments.add("\""+options.getAuthor()+"\"");
        }
        if(options.getAuthor()!=null && !options.getAuthor().isEmpty()){
            arguments.add("--pdfkeywords");
            arguments.add("\""+options.getKeyWords()+"\"");
        }
        if(options.getPdfCompact()!=null){
            arguments.add("--pdfcompat");
            arguments.add(PDFCompact.getValue(options.getPdfCompact()));
        }
        return this;
    }

    public ScanBuilder withDeskewEnabled(boolean enabled){
        if(enabled) {
            arguments.add("--deskew");
        }
        return this;
    }

    public ScanBuilder withRotation(int degrees){
        arguments.add("--rotate");
        arguments.add(String.valueOf(degrees));
        return this;
    }
    public ScanBuilder withOCRenabled(boolean enabled,String lang){
        if(enabled) {
            arguments.add("--enableocr");
            arguments.add("--ocrlang");
            arguments.add(lang);
        }else {
            arguments.add("--disableocr");
        }
        return this;
    }
    public ScanBuilder showProgress(boolean show){
        if(show){
            arguments.add("--progress");
        }
        return this;
    }
    public ScanResult execute(String outputPath) throws IOException,ScanExceptions {
        List<String> fullArgs = new ArrayList<>();
        fullArgs.add("-o");
        fullArgs.add(outputPath);
        if(arguments.contains("-f")){
            arguments.remove("-f");
            fullArgs.add("-f");
        }
        fullArgs.addAll(arguments);
        if(arguments.contains("--progress")){
            arguments.remove("--progress");
            fullArgs.remove("--progress");
            fullArgs.add("--progress");
        }
        return wrapper.executeCommand(fullArgs.toArray(new String[0]));
    }
}