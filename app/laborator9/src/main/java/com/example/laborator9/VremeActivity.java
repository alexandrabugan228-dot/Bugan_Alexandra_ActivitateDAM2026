package com.example.laborator9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VremeActivity extends AppCompatActivity {

    private static final String API_KEY = "cheie api";

    private EditText etOras;
    private Spinner spPerioada;
    private TextView tvVreme;
    private String cheieOras = null;

    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vreme);

        etOras = findViewById(R.id.etOras);
        spPerioada = findViewById(R.id.spPerioada);
        tvVreme = findViewById(R.id.tvVreme);
        Button btnCauta = findViewById(R.id.btnCauta);

        btnCauta.setOnClickListener(v -> {
            String oras = etOras.getText().toString().trim();
            if (oras.isEmpty()) {
                Toast.makeText(this, "Introdu un oraș", Toast.LENGTH_SHORT).show();
                return;
            }
            String metoda = spPerioada.getSelectedItemPosition() == 0 ? "1day" : "5day";
            cautaOras(oras, metoda);
        });
    }

    private String citeste(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int cod = conn.getResponseCode();
        if (cod != 200) {
            conn.disconnect();
            throw new Exception("Eroare server: " + cod);
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder builder = new StringBuilder("");
        String linie;
        while ((linie = reader.readLine()) != null) {
            builder.append(linie);
        }
        reader.close();
        conn.disconnect();
        return builder.toString();
    }

    private void cautaOras(String oras, String metoda) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String rezultat;
                try {
                    String link = "https://dataservice.accuweather.com/locations/v1/cities/search?"
                            + "apikey=" + API_KEY
                            + "&q=" + URLEncoder.encode(oras, "UTF-8");

                    JSONArray vector = new JSONArray(citeste(link));
                    if (vector.length() == 0) {
                        rezultat = "Orașul nu a fost găsit";
                    } else {
                        JSONObject obiect = vector.getJSONObject(0);
                        cheieOras = obiect.getString("Key");
                        String nume = obiect.getString("EnglishName");
                        Log.d("VREME", "Cheie oras: " + cheieOras);

                        String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/"
                                + metoda + "/" + cheieOras
                                + "?apikey=" + API_KEY
                                + "&metric=true";

                        JSONObject radacina = new JSONObject(citeste(linkVreme));
                        JSONArray zile = radacina.getJSONArray("DailyForecasts");

                        StringBuilder text = new StringBuilder();
                        text.append(nume).append(": cheie ").append(cheieOras).append("\n");

                        for (int i = 0; i < zile.length(); i++) {
                            JSONObject zi = zile.getJSONObject(i);
                            String data = zi.getString("Date").substring(0, 10);
                            JSONObject temperatura = zi.getJSONObject("Temperature");
                            double min = temperatura.getJSONObject("Minimum").getDouble("Value");
                            double max = temperatura.getJSONObject("Maximum").getDouble("Value");

                            text.append("\n").append(data).append("\n")
                                    .append("Minimă: ").append(min).append(" grade C\n")
                                    .append("Maximă: ").append(max).append(" grade C\n");
                        }
                        rezultat = text.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    rezultat = "Eroare: " + e.getMessage();
                }

                final String afisare = rezultat;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvVreme.setText(afisare);
                        Toast.makeText(VremeActivity.this, "Gata", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}