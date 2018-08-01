package br.edu.ifma.felipe.appavaliafilme;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dao.DAOAvaliaFilme;
import modelo.AvaliaFilme;

public class MostrarDados extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtNota;
    private TextView txtAno;
    private TextView txtDescricao;
    private TextView txtGenero;
    private SQLiteDatabase db;
    private String nome;
    private String ano;
    private String genero;
    private String descricao;
    private int nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_dados);

        Intent itrecepcao = getIntent();

        Bundle parametros = itrecepcao.getExtras();
        nome = parametros.getString("nome");
        ano = parametros.getString("ano");
        genero = parametros.getString("genero");
        nota = parametros.getInt("nota");
        descricao = parametros.getString("descricao");

        txtNome = findViewById(R.id.txtNomeConteudo);
        txtAno = findViewById(R.id.txtAnoConteudo);
        txtGenero = findViewById(R.id.txtGeneroConteudo);
        txtNota = findViewById(R.id.txtNotaConteudo);
        txtDescricao = findViewById(R.id.txtDescricaoConteudo);

        txtNome.setText(nome);
        txtAno.setText(ano);
        txtGenero.setText(genero);
        txtNota.setText(String.valueOf(nota));
        txtDescricao.setText(descricao);

    }

}
