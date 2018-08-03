package br.edu.ifma.felipe.appavaliafilme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import dao.DAOAvaliaFilme;
import modelo.AvaliaFilme;

public class FormAvaliacao extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNome;
    private EditText txtAno;
    private Spinner spnGeneros;
    private RadioGroup rdEstrelas;
    private EditText txtDescricao;
    private Button btnSalvar;
    private Button btnListar;
    private Button btnLimpar;
    private DAOAvaliaFilme dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_avaliacao);

        String generos[] = {"Ação", "Aventura", "Terror", "Suspense", "Animação", "Comédia"};

        txtNome = findViewById(R.id.txtNome);
        txtAno = findViewById(R.id.txtAno);
        spnGeneros = findViewById(R.id.spnGenero);
        rdEstrelas = findViewById(R.id.rdEstrelas);
        txtDescricao = findViewById(R.id.txtDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnListar = findViewById(R.id.btnListar);
        btnLimpar = findViewById(R.id.btnLimpar);

        btnSalvar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnLimpar.setOnClickListener(this);
        ArrayAdapter<String> adapterGeneros = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,generos);
        spnGeneros.setAdapter(adapterGeneros);

        dao = new DAOAvaliaFilme(this);
    }

    @Override
    public void onClick(View v) {
        int idBotaoClicado = 0;
        idBotaoClicado = v.getId();

        switch (idBotaoClicado) {
            case R.id.btnSalvar:
                recebeDados();
                break;
            case R.id.btnListar:
                listaFilmes();
                break;
            case R.id.btnLimpar:
                limparForm();
                break;
        }
    }

    public void recebeDados() {
        String nome = "";
        String ano = "";
        String genero = "";
        int opcaoRadio = rdEstrelas.getCheckedRadioButtonId();
        int nota = 0;
        String descricao = "";

        nome = txtNome.getText().toString();
        ano = txtAno.getText().toString();
        genero = spnGeneros.getSelectedItem().toString();
        descricao = txtDescricao.getText().toString();

        switch (opcaoRadio) {
            case R.id.rdEstrela1:
                nota = 1;
                break;
            case R.id.rdEstrela2:
                nota = 2;
                break;
            case R.id.rdEstrela3:
                nota = 3;
                break;
            case R.id.rdEstrela4:
                nota = 4;
                break;
            case R.id.rdEstrela5:
                nota = 5;
                break;
        }

        AvaliaFilme af = new AvaliaFilme();
        af.setNome(nome);
        af.setAno(ano);
        af.setGenero(genero);
        af.setNota(nota);
        af.setDescricao(descricao);

        String msg = "Filme adicionado com sucesso";
        boolean result = dao.salvar(af);

        if(!result) {
            msg = "Erro ao salvar";
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void listaFilmes(){
        Intent it = new Intent(this, ListaFilmes.class);
        startActivity(it);
    }

    public void limparForm()
    {
        txtNome.setText(null);
        txtAno.setText(null);
        txtDescricao.setText(null);
        RadioButton radioChecked = findViewById(rdEstrelas.getCheckedRadioButtonId());
        radioChecked.setChecked(false);

    }
}
