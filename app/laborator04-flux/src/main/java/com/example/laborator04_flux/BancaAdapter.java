package com.example.laborator04_flux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class BancaAdapter extends BaseAdapter {

    private List<Banca> banci;
    private Context ctx;
    private int resursaLayout;

    public BancaAdapter(List<Banca> banci, Context ctx, int resursaLayout) {
        this.banci = banci;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return banci.size();
    }

    @Override
    public Object getItem(int i) {
        return banci.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView culoareTV = v.findViewById(R.id.culoareTV);
        TextView locuriTV = v.findViewById(R.id.locuriTV);
        TextView zonaTV = v.findViewById(R.id.zonaTV);
        TextView picioareTV = v.findViewById(R.id.picioareTV);
        CheckBox ocupataCB = v.findViewById(R.id.ocupataCB);
        CheckBox confortCB = v.findViewById(R.id.confortCB);

        Banca b = (Banca) getItem(i);

        culoareTV.setText("Banca " + b.getCuloare());
        locuriTV.setText(b.getLocuri() + "locuri");
        zonaTV.setText(b.getZona());
        picioareTV.setText(b.getNrPicioare() + "picioare");
        ocupataCB.setChecked(b.isEsteOcupata());
        confortCB.setChecked(b.isEsteConfortabila());

        return v;
    }
}