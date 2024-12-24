package com.naps2.main;

import com.naps2.constants.*;
import com.naps2.devices.Device;
import com.naps2.exceptions.ScanExceptions;
import com.naps2.naps.NAPS2;
import com.naps2.pdf.PDFOptions;
import com.naps2.results.ScanResult;

import java.io.IOException;
import java.util.List;

public class Scanner {

    public static void main(String[] arg) throws IOException,ScanExceptions {
        NAPS2 naps=new NAPS2();

        List<Device> devices = naps.listDevices(Driver.TWAIN);
        PDFOptions options = new PDFOptions();
        options.setName("TEST");
        options.setAuthor("Gokul");
        options.setKeyWords("TEST");
        options.setSubject("Test");
        options.setPdfCompact(PDFCompact.A2_b);
        ScanResult advancedResult = naps.createScanBuilder()
                .withDevice(devices.get(0))
                .withDpi(300)
                .withMode(Mode.COLOR)
                .withPDFOptions(options)
                .withSource(Source.duplex)
                .forceFileWrite(true)
                .withOCRenabled(true,"eng")
                .withDeskewEnabled(true)
                //.withRotation(60)
                .withPageSize(PageSize.A4)
                .showProgress(true)
                .execute("\""+System.getProperty("user.dir")+"/output2.pdf\"");
        System.out.println(advancedResult.getOutput());
    }
}
