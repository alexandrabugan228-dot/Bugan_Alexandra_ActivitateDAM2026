package com.example.laborator04_flux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private final ArrayList<Banca> listaBanci = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcherLista =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Banca> modificata =
                            (ArrayList<Banca>) result.getData().getSerializableExtra("lista");
                    if (modificata != null) {
                        listaBanci.clear();
                        listaBanci.addAll(modificata);
                    }
                }
            });

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Banca b = result.getData().getParcelableExtra("banca");
                    if (b != null) {
                        listaBanci.add(b);
                        tvRezultat.setText(b.toString());
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRezultat = findViewById(R.id.tvRezultat);

        findViewById(R.id.btnAdauga).setOnClickListener(v ->
                launcher.launch(new Intent(this, AdaugaBanca.class)));

        findViewById(R.id.btnAdaugaAI).setOnClickListener(v ->
                launcher.launch(new Intent(this, AdaugaBancaAI.class)));

        findViewById(R.id.btnGalerie).setOnClickListener(v ->
                startActivity(new Intent(this, GalerieBanci.class)));

        Button btn = findViewById(R.id.btnVizualizare);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AfisareBanci.class);
                it.putExtra("lista", listaBanci);
                it.putExtra("lista", listaBanci);
                launcherLista.launch(it);
            }
        });







    }


}