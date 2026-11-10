package com.example.laborator04_flux;

import android.graphics.Bitmap;

public class ImagineBanca {

    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImagineBanca(String textAfisat, Bitmap imagine, String link) {
        this.textAfisat = textAfisat;
        this.imagine = imagine;
        this.link = link;
    }

    public String getTextAfisat() { return textAfisat; }
    public Bitmap getImagine() { return imagine; }
    public String getLink() { return link; }
}