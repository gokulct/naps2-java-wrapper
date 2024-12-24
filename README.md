# NAPS2-Java-Wrapper  
Java scanning library based on NAPS2 - Not Another PDF Scanner 2  

## Overview  
This library serves as a Java wrapper for the NAPS2 (Not Another PDF Scanner 2) scanning tool. It leverages the NAPS2 Command-Line Interface (CLI) to provide an easy-to-use Java class-based interface for scanning operations.  

With this wrapper, developers can seamlessly integrate advanced scanning features into their Java applications. It supports all major features of NAPS2, including TWAIN, WIA, SANE, and other driver protocols.  

Visit the [official NAPS2 website](https://www.naps2.com/) for more information about its features and capabilities.  

---

## Features  
- Support for multiple scanning drivers: **TWAIN**, **WIA**, **SANE**, **APPLE**, and **ESCL**.  
- Device discovery with driver type filtering.  
- Advanced scan customization options:  
  - DPI settings  
  - Color modes  
  - Page sizes  
  - Duplex scanning  
  - OCR support with language configuration  
  - PDF metadata customization  
  - Image rotation and deskewing  
- Flexible output formats, including compact PDF options.  
- Progress tracking during scan operations.  
- Compatibility with multiple NAPS2 Console library versions.  
---

## Usage  

### Basic Scanning Example  
```java
NAPS2 naps = new NAPS2();

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
    .withSource(Source.DUPLEX)
    .forceFileWrite(true)
    .withOCRenabled(true, "eng")
    .withDeskewEnabled(true)
    .withRotation(90)
    .withPageSize(PageSize.A4)
    .showProgress(true)
    .execute("\"" + System.getProperty("user.dir") + "/output2.pdf\"");
```
If you prefer to use a custom version of the NAPS2 Console library, you can specify its path explicitly in your Java code as shown below:  
```java
NAPS2 naps = new NAPS2("/path/to/naps2/console/library/NAPS2.Console.exe");
```
