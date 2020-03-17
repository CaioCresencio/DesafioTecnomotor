package com.tecnomotor.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnomotor.desafio.model.Montadora;
import com.tecnomotor.desafio.service.HTTPServiceMontadora;
import com.tecnomotor.desafio.service.HTTPServiceTipo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private TextView textView;
    private List<Montadora> mmontadoras;
    private List<String> tipos;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);

        addItemsOnSpinner();
        Button btn = (Button) findViewById(R.id.btn_tipo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(MainActivity.this, MontadoraActivity.class);
                startActivity(tela);
            }
        });
    }

    public void getTipos(){
        HTTPServiceTipo serviceTipo = new HTTPServiceTipo();
        HTTPServiceMontadora serviceMontadora;
        try {
            tipos = serviceTipo.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Montadora> getMontadoras(String tipo){
        HTTPServiceTipo serviceTipo = new HTTPServiceTipo();
        HTTPServiceMontadora serviceMontadora;
        List<Montadora> lista = new ArrayList<>();
        try {
            serviceMontadora = new HTTPServiceMontadora(tipo);
            lista = serviceMontadora.execute().get();
            textView.setText(mmontadoras.get(0).getNome());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void addItemsOnSpinner(){
        getTipos();

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipos);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),tipos.get(i), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
