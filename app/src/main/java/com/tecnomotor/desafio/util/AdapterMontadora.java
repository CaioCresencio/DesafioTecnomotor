package com.tecnomotor.desafio.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tecnomotor.desafio.R;
import com.tecnomotor.desafio.model.Montadora;

import java.util.List;

public class AdapterMontadora extends BaseAdapter {

    private List<Montadora> montadoras;
    private Activity act;

    public AdapterMontadora(List<Montadora> montadoras, Activity act){
        this.montadoras = montadoras;
        this.act = act;
    }
    @Override
    public int getCount() {
        return montadoras.size();
    }

    @Override
    public Object getItem(int i) {
        return montadoras.get(i);
    }

    @Override
    public long getItemId(int i) {
        return montadoras.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view_return = act.getLayoutInflater().inflate(R.layout.activity_item_montadora,viewGroup,false);
        Montadora montadora = montadoras.get(i);
        TextView nome = (TextView) view_return.findViewById(R.id.nome_montadora);

        nome.setText(montadora.getNome());
        return view_return;
    }
}
