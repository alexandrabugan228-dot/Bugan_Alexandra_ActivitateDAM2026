package com.example.laborator04_flux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AfisareBanci extends AppCompatActivity {

    private ArrayList<Banca> lista ;
    private int idModificat = 0;
    private BancaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_banci);

        lista = getIntent().getParcelableArrayListExtra("lista");
        if (lista == null) {
            lista = new ArrayList<>();
        }

        ListView lv = findViewById(R.id.lvBanci);
        adapter = new BancaAdapter(lista, getApplicationContext(), R.layout.rand_banca);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaBanca.class);
                intentModifica.putExtra("banca", lista.get(i));
                idModificat = i;
                startActivityForResult(intentModifica, 209);
                Toast.makeText(getApplicationContext(), "click " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                lista.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Bancă ștearsă", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 209) {
                lista.set(idModificat, (Banca) data.getParcelableExtra("banca"));
                adapter.notifyDataSetChanged();
            }
        }
    }


}