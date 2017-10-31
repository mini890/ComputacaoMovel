package ismai.spinner;

/**
 * Created by migue on 31-Oct-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
        String[] colunas = new String[2];
        colunas[0] = "numero";
        colunas[1] = "nome";
        return database.query("alunos", colunas, null, null, null, null, "nome");
    }

    public List<String> obterTodosNumeros() {
        ArrayList<String> numeros = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                numeros.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return numeros;
    }

    public List<String> obterTodosNomes() {
        ArrayList<String> nomes = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return nomes;
    }

    public int obterTodosCampos(List<Integer> osIds, List<String> osNumeros, List<String> osNomes) {
        String[] colunas = new String[23];
        colunas[0] = "_id";
        colunas[1] = "numero";
        colunas[2] = "nome";
        Cursor c = database.query("alunos", colunas, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                osIds.add(c.getInt(0));
                osNumeros.add(c.getString(1));
                osNomes.add(c.getString(2));
            } while (c.moveToNext());
        }
        c.close();
        return osIds.size();
    }

    public boolean existe(String umNome) {
        Cursor cursor = database.rawQuery(
                "select nome from alunos where nome=?", new String[]{umNome});
        boolean b = cursor.getCount() >= 1;
        cursor.close();
        return b;
    }

    public long insertNumeroNome(String oNumero, String oNome) {
        ContentValues values = new ContentValues();
        values.put("numero", oNumero);
        values.put("nome", oNome);
        return database.insert("alunos", null, values);
    }

    public int deleteNome(String oNome) {
        String whereClause = "nome = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = oNome;
        return database.delete("alunos", whereClause, whereArgs);
    }

    public int deleteTodosNomes() {
        return database.delete("alunos", null, null);
    }

    public int updateNome(Integer oId, String oNumero, String oNome) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("nome", oNome);
        values.put("numero", oNumero);
        return database.update("alunos", values, whereClause, whereArgs);
    }
}