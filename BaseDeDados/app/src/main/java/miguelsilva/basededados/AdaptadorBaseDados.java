package miguelsilva.basededados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by migue on 24-Oct-17.
 */

public class AdaptadorBaseDados {
    private AjudaUsoBaseDados dbHelper;
    private SQLiteDatabase database;

    public AdaptadorBaseDados(Context context) {
        dbHelper = new AjudaUsoBaseDados(context.getApplicationContext());
    }

    public AdaptadorBaseDados open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor obterTodosRegistos() {
        String[] colunas = new String[4];
        colunas[0] = "nome";
        colunas[1] = "morada";
        colunas[2] = "telefone";
        return database.query("dados", colunas, null, null, null, null, "nome");
    }

    public List<String> obterTodosNomes() {
        ArrayList<String> nomes = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return nomes;
    }

    public List<String> obterTodosMoradas() {
        ArrayList<String> moradas = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                moradas.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return moradas;
    }

    public List<String> obterTodosTelefones() {
        ArrayList<String> telefones = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                telefones.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return telefones;
    }

    public int obterTodosCampos(List<Integer> osIds, List<String> osNomes, List<String> osMoradas, List<String> osTelefones) {
        String[] colunas = new String[23];
        colunas[0] = "_id";
        colunas[1] = "nome";
        colunas[2] = "morada";
        colunas[3] = "telefone";
        Cursor c = database.query("dados", colunas, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                osIds.add(c.getInt(0));
                osNomes.add(c.getString(1));
                osMoradas.add(c.getString(2));
                osTelefones.add(c.getString(3));
            } while (c.moveToNext());
        }
        c.close();
        return osIds.size();
    }

    public boolean existe(String umNome) {
        Cursor cursor = database.rawQuery(
                "select nome from dados where nome=?", new String[]{umNome});
        boolean b = cursor.getCount() >= 1;
        cursor.close();
        return b;
    }

    public long insertNomeMoradaTelefone(String oNome, String oMorada, String oTelefone) {
        ContentValues values = new ContentValues();
        values.put("nome", oNome);
        values.put("morada", oMorada);
        values.put("telefone", oTelefone);
        return database.insert("dados", null, values);
    }
}