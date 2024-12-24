package com.scan.pdf;

import com.scan.constants.PDFCompact;

public class PDFOptions {
    private String name;
    private String author;
    private String subject;
    private String keyWords;
    private PDFCompact pdfCompact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public PDFCompact getPdfCompact() {
        return pdfCompact;
    }

    public void setPdfCompact(PDFCompact pdfCompact) {
        this.pdfCompact = pdfCompact;
    }
}
