package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.AvaliaFilme;
import util.DBGateway;

public class DAOAvaliaFilme {
    private final String TABELA = "avaliafilme";
    private DBGateway gw;
    private ArrayList<AvaliaFilme> lista;

    public DAOAvaliaFilme(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public boolean salvar(AvaliaFilme af) {
        boolean retorno = false;
        ContentValues valores = new ContentValues();

        valores.put("nome", af.getNome());
        valores.put("ano", af.getAno());
        valores.put("genero", af.getGenero());
        valores.put("nota", af.getNota());
        valores.put("descricao", af.getDescricao());

        long result = gw.getDatabase().insert(TABELA, null, valores);
        if (result > 0) {
            retorno = true;
        }

        return retorno;
    }

    public ArrayList<AvaliaFilme> getFilmes()
    {
        ArrayList<AvaliaFilme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM avaliafilme ORDER BY id DESC";

        String[] campos = {"id", "nome", "ano", "genero", "nota", "descricao"};

        Cursor cs = gw.getDatabase().query("avaliafilme", campos, null, null, null, null, "id");

        while (cs.moveToNext()) {
            AvaliaFilme filme = new AvaliaFilme();

            filme.setId(cs.getInt(0));
            filme.setNome(cs.getString(1));
            filme.setAno(cs.getString(2));
            filme.setGenero(cs.getString(3));
            filme.setNota(cs.getInt(4));
            filme.setDescricao(cs.getString(5));

            filmes.add(filme);
        }

        cs.close();
        return filmes;
    }
}
