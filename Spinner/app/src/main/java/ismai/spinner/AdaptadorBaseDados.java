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
        colunas[0] = "nome";
        colunas[1] = "morada";
        colunas[2] = "telefone";
        //Ordernado por nome
        //return database.query("alunos", colunas, null, null, null, null, "nome");
        return database.query("pessoas", colunas, null, null, null, null, null);
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

    public List<String> obterTodasMoradas() {
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

    public int obterTodosCampos(List<Integer> osIds, List<String> osNomes, List<String> asMoradas, List<String> osTelefones) {
        String[] colunas = new String[23];
        colunas[0] = "_id";
        colunas[1] = "nome";
        colunas[2] = "morada";
        colunas[3] = "telefone";
        Cursor c = database.query("pessoas", colunas, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                osIds.add(c.getInt(0));
                osNomes.add(c.getString(1));
                asMoradas.add(c.getString(2));
                osTelefones.add(c.getString(3));
            } while (c.moveToNext());
        }
        c.close();
        return osIds.size();
    }

    public boolean existe(String umNome) {
        Cursor cursor = database.rawQuery(
                "select nome from pessoas where nome=?", new String[]{umNome});
        boolean b = cursor.getCount() >= 1;
        cursor.close();
        return b;
    }

    public long insertNomeMoradaTelefone(String nome, String morada, String telefone) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("morada", morada);
        values.put("telefone", telefone);
        return database.insert("pessoas", null, values);
    }

    public int updateDados(Integer oId, String nome, String morada, String telefone) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("morada", morada);
        values.put("telefone", telefone);
        return database.update("pessoas", values, whereClause, whereArgs);
    }
}