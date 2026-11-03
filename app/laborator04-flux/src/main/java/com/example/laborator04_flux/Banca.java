package com.example.laborator04_flux;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Banca implements Parcelable {

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

    protected Banca(Parcel in) {
        culoare = in.readString();
        locuri = in.readInt();
        esteOcupata = in.readByte() != 0;
        zona = in.readString();
        nrPicioare = in.readInt();
        esteConfortabila = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(culoare);
        dest.writeInt(locuri);
        dest.writeByte((byte) (esteOcupata ? 1 : 0));
        dest.writeString(zona);
        dest.writeInt(nrPicioare);
        dest.writeByte((byte) (esteConfortabila ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Banca> CREATOR = new Creator<Banca>() {
        @Override
        public Banca createFromParcel(Parcel in) {
            return new Banca(in);
        }

        @Override
        public Banca[] newArray(int size) {
            return new Banca[size];
        }
    };

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

