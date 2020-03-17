package com.tecnomotor.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnomotor.desafio.model.Montadora;
import com.tecnomotor.desafio.util.AdapterMontadora;

import java.util.ArrayList;
import java.util.List;

public class MontadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montadora);
        ArrayList<Montadora> montadoras = (ArrayList<Montadora>) getIntent().getSerializableExtra("montadoras");

        ListView listview = (ListView) findViewById(R.id.listview);

        AdapterMontadora adapterMontadora = new AdapterMontadora(montadoras,this);

        listview.setAdapter(adapterMontadora);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicou no item " + position, Toast.LENGTH_LONG).show();
            }
        });
      
    }
}
