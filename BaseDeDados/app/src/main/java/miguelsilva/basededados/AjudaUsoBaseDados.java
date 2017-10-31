package miguelsilva.basededados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by migue on 24-Oct-17.
 */

public class AjudaUsoBaseDados extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "basedados.db";
    private static final int VERSION = 1;

    public AjudaUsoBaseDados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "CREATE TABLE dados(_id integer primary key autoincrement, nome varchar(40), morada varchar(255), telefone integer)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS Alunos");
        //onCreate(db);
    }

}