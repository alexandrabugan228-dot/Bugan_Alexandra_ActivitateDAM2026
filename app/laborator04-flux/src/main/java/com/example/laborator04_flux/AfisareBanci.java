package com.example.laborator04_flux;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AfisareBanci extends AppCompatActivity {

    private ArrayList<Banca> lista;
    private ArrayAdapter<Banca> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_banci);
        lista = (ArrayList<Banca>) getIntent().getSerializableExtra("lista");
        if (lista == null) {
            lista = new ArrayList<>();
        }
        ListView lv = findViewById(R.id.lvBanci);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Banca b = lista.get(position);
            Toast.makeText(this, b.toString(), Toast.LENGTH_LONG).show();
        });

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "banca stearsa", Toast.LENGTH_SHORT).show();
            return true;
        });

    }
    private void inchide() {
        Intent rezultat = new Intent();
        rezultat.putExtra("lista", lista);
        setResult(RESULT_OK, rezultat);
        finish();
    }

}