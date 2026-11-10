package com.example.laborator04_flux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineBancaAdapter extends BaseAdapter {

    private Context ctx;
    private int resursaLayout;
    private List<ImagineBanca> lista;

    public ImagineBancaAdapter(Context ctx, int resursaLayout, List<ImagineBanca> lista) {
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        ImageView imagineIV = v.findViewById(R.id.imagineIV);
        TextView textTV = v.findViewById(R.id.textTV);

        ImagineBanca ib = (ImagineBanca) getItem(i);
        imagineIV.setImageBitmap(ib.getImagine());
        textTV.setText(ib.getTextAfisat());

        return v;
    }
}