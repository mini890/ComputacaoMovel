package pt.ismai.a029187.xmlparser;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.StringReader;
import java.util.List;

public class MainActivity extends ListActivity {

    protected AsyncGenerator backgroundTask;
    protected List<Integer> lista = null;
    protected ArrayAdapter<Integer> adapter;
    protected Activity activity;
    protected Intent intent;

    private void openActivity(Class<?> subActivity, int id) {
        Intent intent = new Intent(this, subActivity);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        backgroundTask = new AsyncGenerator("thomas-bayer.com", "/sqlrest/CUSTOMER", 80);
    }

    @Override
    protected void onStart() {
        super.onStart();
        backgroundTask.execute();
        intent = getIntent();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        int id_customer = (Integer) getListView().getItemAtPosition(position);
        openActivity(Customer.class, id_customer);
    }

    private class AsyncGenerator extends AsyncTask<Integer, Void, String> {

        protected String host, path;
        protected int port;

        AsyncGenerator(String host, String path, int port) {
            this.host = host;
            this.path = path;
            this.port = port;
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return Comunicar.contactar2(host, path, port);
        }

        @Override
        protected void onPostExecute(String s) {
            lista = null;
            try {
                SaxXmlParser<Integer, SaxCustomerXmlHandler> parser = new SaxXmlParser<Integer, SaxCustomerXmlHandler>();
                parser.setHandler(new SaxCustomerXmlHandler());
                lista = parser.parse(new StringReader(s));
                adapter = new ArrayAdapter<Integer>(activity, android.R.layout.simple_list_item_1, lista);
                setListAdapter(adapter);
            } catch (Exception e) {
            }
        }
    }
}