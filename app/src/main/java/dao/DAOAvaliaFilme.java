package dao;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import modelo.AvaliaFilme;
import util.DBGateway;

public class DAOAvaliaFilme {
    private final String TABELA = "avalia_filme";
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
}
