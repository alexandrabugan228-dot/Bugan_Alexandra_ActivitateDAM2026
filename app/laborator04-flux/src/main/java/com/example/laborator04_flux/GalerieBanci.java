package com.example.laborator04_flux;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GalerieBanci extends AppCompatActivity {

    private List<ImagineBanca> lista = null;
    private List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie_banci);

        final List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://www.mobilierurbanmodern.ro/wp-content/uploads/2018/06/UM337B.jpg");
        linkuriImagini.add("https://dupex.ro/wp-content/uploads/2018/06/BT.12.jpeg");
        linkuriImagini.add("https://inovi.ro/wp-content/uploads/2024/02/11CSB-5-2-1024x1024.jpg");
        linkuriImagini.add("https://www.altdepozit.ro/continut/produse/19690/420/bd-image-114116-afs-00-00-00-6520473-42711.jpg");
        linkuriImagini.add("https://metalgan.ro/mag/image/cache/catalog/Banci/IMG_1760-500x500.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestProperty("User-Agent", "Laborator8App/1.0 (student)");
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        e.printStackTrace();
                        imagini.add(null);
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        lista.add(new ImagineBanca("banca fara spatar", imagini.get(0),
                                "https://www.mobilierurbanmodern.ro/produs/banca-plastic-reciclat-hyde-park-fara-spatar/"));
                        lista.add(new ImagineBanca("banca langa iarba", imagini.get(1),
                                "https://dupex.ro/produs/banca-parc-picioare-teava-pin-stejar-bt-12/"));
                        lista.add(new ImagineBanca("banca smart autobuz", imagini.get(2),
                                "https://inovi.ro/banci-smart-parc-mihai-eminescu-botosani/"));
                        lista.add(new ImagineBanca("banca albastra", imagini.get(3),
                                "https://www.altdepozit.ro/banca-pentru-parc-din-plastic-cu-13-sipci-p19690/?srsltid=AYCX9vLRwHIlW3Bjxf1pZsyD_PgO7gOoV-6E07wDilsQoPQbpKmZ7PSE"));
                        lista.add(new ImagineBanca("banca cu acoperis", imagini.get(4),
                                "https://metalgan.ro/mag/banci-parc-si-gradina-premium/banca-parc-si-gradina-b135"));

                        ListView lv = findViewById(R.id.imagini);
                        ImagineBancaAdapter adapter = new ImagineBancaAdapter(
                                getApplicationContext(), R.layout.imagine_banca_layout, lista);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent it = new Intent(getApplicationContext(), WebBancaActivity.class);
                                it.putExtra("link", lista.get(i).getLink());
                                startActivity(it);
                            }
                        });
                    }
                });
            }
        });
    }
}