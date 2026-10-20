package com.example.laborator04_flux;


import java.io.Serializable;

public class Banca implements Serializable {

    private String culoare;
    private int locuri;
    private boolean esteOcupata;
    private String zona;
    private int nrPicioare;
    private boolean esteConfortabila;

    public Banca(String culoare, int locuri, boolean esteOcupata, String zona, int nrPicioare, boolean esteConfortabila){
        this.culoare=culoare;
        this.locuri=locuri;
        this.esteOcupata=esteOcupata;
        this.zona=zona;
        this.nrPicioare=nrPicioare;
        this.esteConfortabila=esteConfortabila;
    }

    public String getCuloare() { return culoare; }
    public int getLocuri() { return locuri; }
    public boolean isEsteOcupata() { return esteOcupata; }
    public String getZona() { return zona; }
    public int getNrPicioare() { return nrPicioare; }

    public boolean isEsteConfortabila() {
        return esteConfortabila;
    }

    @Override
    public String toString() {
        return "Banca{" +
                "culoare='" + culoare + '\'' +
                ", locuri=" + locuri +
                ", esteOcupata=" + esteOcupata +
                ", zona='" + zona + '\'' +
                ", nrPicioare=" + nrPicioare +
                ", esteConfortabila=" + esteConfortabila +
                '}';
    }
}
