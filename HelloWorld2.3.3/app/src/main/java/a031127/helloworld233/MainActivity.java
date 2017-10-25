package a031127.helloworld233;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends Activity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("pontuacoes.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        inserir("Android 2.3.3", 69);
    }

    protected void inserir(String nome, int valor) {
        String cr = "create table pontuacao(_id integer primary key autoincrement, nome text, valor integer)";
        String drop = "drop table if exists pontuacao";
        String sqlCommand = "INSERT INTO pontuacao (nome, valor) VALUES ('" + nome + "', " + valor + ")";

        //db.execSQL(drop);
        //db.execSQL(cr);
        db.execSQL(sqlCommand);
    }
}