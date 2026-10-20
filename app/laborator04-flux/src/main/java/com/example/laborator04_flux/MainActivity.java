package com.example.laborator04_flux;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }
    public void AdaugaBanca(View view)
    {
        EditText et=findViewById(R.id.culoare_et);
        CheckBox cb=findViewById(R.id.esteOcupata_CB);
        EditText et2=findViewById(R.id.numarLocuri_et);
        EditText et3=findViewById(R.id.zona_et);
        Spinner sp=findViewById(R.id.nrPicioare_sp);
        RatingBar rb=findViewById(R.id.confort_RB);

        String cloare=et.getText().toString();
        Boolean esteOcupata=cb.isChecked();
        Integer numar=et2.getText().

    }
}