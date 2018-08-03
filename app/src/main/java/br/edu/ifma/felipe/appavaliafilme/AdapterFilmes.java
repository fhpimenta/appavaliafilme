package br.edu.ifma.felipe.appavaliafilme;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import modelo.AvaliaFilme;

public class AdapterFilmes extends BaseAdapter {
    private final List<AvaliaFilme> filmes;
    private final Activity act;

    public AdapterFilmes(List<AvaliaFilme> filmes, Activity act) {
        this.filmes = filmes;
        this.act = act;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public AvaliaFilme getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_item, parent, false);
        AvaliaFilme filme = filmes.get(position);

        TextView nameFilme = view.findViewById(R.id.nameFilme);
        nameFilme.setText(filme.getNome());

        LinearLayout layout = view.findViewById(R.id.layoutImagem);

        for(int i = 0;i < filme.getNota();i++) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setImageResource(R.drawable.if_star);
            layout.addView(imageView);
        }

        return view;
    }
}
