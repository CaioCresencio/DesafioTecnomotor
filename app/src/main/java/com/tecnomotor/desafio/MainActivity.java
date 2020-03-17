package com.tecnomotor.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.tecnomotor.desafio.model.Montadora;
import com.tecnomotor.desafio.service.HTTPServiceMontadora;
import com.tecnomotor.desafio.service.HTTPServiceTipo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private List<Montadora> mmontadoras;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        HTTPServiceTipo serviceTipo = new HTTPServiceTipo();
        HTTPServiceMontadora serviceMontadora;
        try {
            List<String> lista = serviceTipo.execute().get();
            serviceMontadora = new HTTPServiceMontadora(lista.get(0));
            mmontadoras = serviceMontadora.execute().get();
            textView.setText(mmontadoras.get(0).getNome());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
