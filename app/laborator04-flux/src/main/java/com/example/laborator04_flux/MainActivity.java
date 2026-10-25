package com.example.laborator04_flux;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Banca b = (Banca) result.getData().getSerializableExtra("banca");
                    if (b != null) {
                        tvRezultat.setText(b.toString());
                        Toast.makeText(this, "Banca adăugată: " + b.getCuloare(),
                                Toast.LENGTH_LONG).show();
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

    }


}