package com.tecnomotor.desafio.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tecnomotor.desafio.model.Montadora;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HTTPServiceMontadora extends AsyncTask<Void, Void, ArrayList<Montadora>> {
    private String tipo;

    public HTTPServiceMontadora(String tipo){
        this.tipo = tipo;
    }
    @Override
    protected ArrayList<Montadora> doInBackground(Void... voids) {
        StringBuilder sb = new StringBuilder();
        Type typeList = new TypeToken<List<Montadora>>(){}.getType();
        try{
            URL url = new URL("https://service.tecnomotor.com.br/iRasther/montadora?pm.type="+this.tipo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                sb.append(scanner.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(sb.toString(), typeList);
    }
}
