package miguelsilva.basededados;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ListarActivity extends ListActivity {
    AdaptadorBaseDados a;
    ArrayAdapter<String> adapter;
    protected ArrayList<String> listItems;
    protected HashMap<String, Integer> indexes;

    private void executarOutraActivity(Class<?> subActividade, int position) {
        Intent intent = new Intent(this, subActividade);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Intent intent = getIntent();
        listItems = intent.getStringArrayListExtra("osNomes");
        indexes = new HashMap<String, Integer>();
        for (int i = 0; i < listItems.size(); i++) {
            indexes.put(listItems.get(i), i);
        }

        Collections.sort(listItems);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        a = new AdaptadorBaseDados(this).open();
    }

    @Override
    protected void onListItemClick(ListView parent, View v, int position, long id) {
        executarOutraActivity(MostrarDados.class, indexes.get(getListAdapter().getItem(position).toString()));
        //Toast.makeText(this, , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        a.close();
    }
}