package br.edu.ifma.felipe.appavaliafilme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import dao.DAOAvaliaFilme;
import modelo.AvaliaFilme;

public class ListaFilmes extends AppCompatActivity {

    private DAOAvaliaFilme dao;
    private AdapterFilmes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        dao = new DAOAvaliaFilme(this);
        List<AvaliaFilme> filmes = dao.getFilmes();

        ListView lista = findViewById(R.id.listFilmes);

        adapter = new AdapterFilmes(filmes, this);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AvaliaFilme filme = adapter.getItem(position);

                detalhes(filme);
            }
        });
    }

    public void detalhes(AvaliaFilme filme)
    {
        Intent it = new Intent(this, MostrarDados.class);
        Bundle params = new Bundle();
        params.putString("nome", filme.getNome());
        params.putString("ano", filme.getAno());
        params.putString("genero", filme.getGenero());
        params.putString("descricao", filme.getDescricao());
        params.putInt("nota", filme.getNota());

        it.putExtras(params);
        startActivity(it);
    }
}
