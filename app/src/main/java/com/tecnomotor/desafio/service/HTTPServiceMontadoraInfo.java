package com.tecnomotor.desafio.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tecnomotor.desafio.model.MontadoraInfo;

import java.io.IOException;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class HTTPServiceMontadoraInfo extends AsyncTask<Void, Void, ArrayList<MontadoraInfo>> {
    private String tipo;
    private int id;

    public HTTPServiceMontadoraInfo(String tipo, int id){
        this.tipo = tipo;
        this.id = id;
    }
    @Override
    protected ArrayList<MontadoraInfo> doInBackground(Void... voids) {
        StringBuilder sb = new StringBuilder();
        Type typeList = new TypeToken<ArrayList<MontadoraInfo>>(){}.getType();
        try{
            URL url = new URL("https://service.tecnomotor.com.br/iRasther/aplicacao?pm.platform=1&pm.version=17&pm.type="+this.tipo+"&pm.assemblers="+this.id+"&pm.pageIndex=0&pm.pageSize=10");
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
        String jsonIdeal = "[" +
                "{" +
                " 'veiculo': { 'id': 1057, 'nome': '159' }," +
                " 'motorizacao': { 'id': 22, 'nome': '1.9-16V' }" +
                "}," +
                "{" +
                " 'veiculo': { 'id': 1097, 'nome': 'Giulietta' }," +
                " 'motorizacao': { 'id': 20, 'nome': '1.4' } " +
                "}," +
                "{" +
                " 'veiculo': { 'id': 21, 'nome': 'Mito' }," +
                " 'motorizacao': { 'id': 20, 'nome': '1.4-16V' } " +
                "}" +
                "]" ;
        /** O principal problema nessa situação é converter apenas o que é necessário do json, uma vez que a estrutura contém diversos valores não utilizados**/
        /** Como não encontrei uma forma de obter apenas o necessário do endpoint, optei por fazer um mock do conteúdo que eu utilizaria para então finalizar o fluxo do app**/

        return new Gson().fromJson(jsonIdeal,typeList);
    }
}
