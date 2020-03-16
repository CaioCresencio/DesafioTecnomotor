package com.tecnomotor.desafio.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class HTTPServiceTipo extends AsyncTask<Void, Void, List<String>> {

    @Override
    protected List<String> doInBackground(Void... voids) {
        StringBuilder sb = new StringBuilder();
        Type list = new TypeToken<List<String>>(){}.getType();
        try{
            URL url = new URL("https://service.tecnomotor.com.br/iRasther/tipo");
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
        return new Gson().fromJson(sb.toString(), list);
    }
}
