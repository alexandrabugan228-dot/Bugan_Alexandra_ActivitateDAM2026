package com.example.laborator04_flux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class AdaugaBanca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_banca);

        findViewById(R.id.btnSalveaza).setOnClickListener(this::adaugaBanca);
    }

    private void adaugaBanca(View view) {
        EditText etCuloare = findViewById(R.id.culoare_et);
        EditText etLocuri = findViewById(R.id.numarLocuri_et);
        Spinner spZona = findViewById(R.id.zona_sp);
        RadioGroup rgPicioare = findViewById(R.id.rgPicioare);
        CheckBox cbOcupata = findViewById(R.id.esteOcupata_CB);
        Switch swConfort = findViewById(R.id.confort_sw);
        RatingBar rbConfort = findViewById(R.id.confort_RB);

        String culoare = etCuloare.getText().toString().trim();
        String locuriStr = etLocuri.getText().toString().trim();

        if (culoare.isEmpty() || locuriStr.isEmpty()) {
            Toast.makeText(this, "Completați toate câmpurile", Toast.LENGTH_SHORT).show();
            return;
        }

        int locuri = Integer.parseInt(locuriStr);
        String zona = spZona.getSelectedItem().toString();
        int picioare = rgPicioare.getCheckedRadioButtonId() == R.id.rb2 ? 2 : 4;
        boolean esteOcupata = cbOcupata.isChecked();
        boolean esteConfortabila = swConfort.isChecked();

        Banca b = new Banca(culoare, locuri, esteOcupata, zona, picioare, esteConfortabila);

        Toast.makeText(this, "rating confort: " + rbConfort.getRating(),
                Toast.LENGTH_SHORT).show();

        Intent rezultat = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("banca", b);
        rezultat.putExtras(bundle);
        setResult(RESULT_OK, rezultat);
        finish();
    }
}