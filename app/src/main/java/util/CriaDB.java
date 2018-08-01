package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbavaliafilme.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public CriaDB(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE avaliafilme(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nome   VARCHAR(255)," +
                    "ano    VARCHAR(10)," +
                    "genero VARCHAR(255)," +
                    "nota   INTEGER," +
                    "descricao  TEXT)";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS avaliafilme");
        onCreate(db);
    }
}
