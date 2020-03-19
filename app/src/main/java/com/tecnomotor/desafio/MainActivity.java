package com.tecnomotor.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnomotor.desafio.model.Montadora;
import com.tecnomotor.desafio.model.MontadoraInfo;
import com.tecnomotor.desafio.service.HTTPServiceMontadoraInfo;
import com.tecnomotor.desafio.service.HTTPServiceMontadora;
import com.tecnomotor.desafio.service.HTTPServiceTipo;
import com.tecnomotor.desafio.util.AdapterMontadora;
import com.tecnomotor.desafio.util.AdapterMontadoraInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private TextView textView;
    private ArrayList<Montadora> montadoras;
    private List<MontadoraInfo> montadoraInfoList;
    private List<String> tipos;
    private Spinner spinner;
    private String tipoSelected = "LEVES";
    ListView listviewInfoHome;

    private int CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        listviewInfoHome = (ListView) findViewById(R.id.listview_info);

        if(montadoraInfoList != null) {
            AdapterMontadoraInfo adapterMontadoraInfo = new AdapterMontadoraInfo(montadoraInfoList, this);
            listviewInfoHome.setAdapter(adapterMontadoraInfo);
        }


        addItemsOnSpinner();
        Button btn = (Button) findViewById(R.id.btn_tipo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(MainActivity.this, MontadoraActivity.class);
                tela.putExtra("montadoras", getMontadoras(tipoSelected));

                startActivityForResult(tela,CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE) {
            if(resultCode == Activity.RESULT_OK){
                Montadora montadora_result = (Montadora) data.getSerializableExtra("montadora_result");
                getMontadoraInfoList(montadora_result);
                AdapterMontadoraInfo adapterMontadoraInfo = new AdapterMontadoraInfo(montadoraInfoList, this);
                listviewInfoHome.setAdapter(adapterMontadoraInfo);

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
    public void getMontadoraInfoList(Montadora montadora){
        HTTPServiceMontadoraInfo httpServiceMontadoraInfo = new HTTPServiceMontadoraInfo(montadora.getTipo(),montadora.getId());
        try {
            this.montadoraInfoList = httpServiceMontadoraInfo.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void getTipos(){
        HTTPServiceTipo serviceTipo = new HTTPServiceTipo();
        try {
            tipos = serviceTipo.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Montadora> getMontadoras(String tipo){
        HTTPServiceMontadora serviceMontadora;
        ArrayList<Montadora> lista = new ArrayList<>();
        try {
            serviceMontadora = new HTTPServiceMontadora(tipo);
            lista = serviceMontadora.execute().get();

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
        this.tipoSelected = tipos.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
