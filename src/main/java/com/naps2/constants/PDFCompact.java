package com.naps2.constants;

public enum PDFCompact {
    A1_b,A2_b,A3_b,A3_u;

    public static String getValue(PDFCompact compact){
        return compact.name().replace("_","-");
    }
}
