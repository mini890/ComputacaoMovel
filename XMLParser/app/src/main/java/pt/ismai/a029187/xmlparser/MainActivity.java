package pt.ismai.a029187.xmlparser;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.StringReader;
import java.util.List;

public class MainActivity extends ListActivity {
    protected List<Integer> listaDeClientes;
    protected String host, path;
    protected int port;
    protected AsyncGenerator task;

    protected ArrayAdapter<Integer> adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host = "thomas-bayer.com";
        path = "sqlrest/CUSTOMER";
        port = 80;

        new AsyncGenerator(host, path, port).execute(99);

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, listaDeClientes);
        setListAdapter(adapter);

        if (listaDeClientes != null) {
            Toast.makeText(this, "" + listaDeClientes.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

    }

    private class AsyncGenerator extends AsyncTask<Integer, Void, String> { //Background, Progresso, Resultado
        protected boolean ignorar;
        protected String host, path;
        protected int port;

        AsyncGenerator(String host, String path, int port) {
            this.host = host;
            this.path = path;
            this.port = port;
        }

        @Override
        protected void onPreExecute() {
            ignorar = false;
            Toast.makeText(MainActivity.this, "doInback", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return Comunicar.contactar2(host, path, port);
        }

        @Override
        protected void onPostExecute(String s) {
            listaDeClientes = null;
            try {
                SaxXmlParser<Integer, SaxCustomerXmlHandler> parser = new SaxXmlParser<Integer, SaxCustomerXmlHandler>();
                parser.setHandler(new SaxCustomerXmlHandler());
                listaDeClientes = parser.parse(new StringReader(s));
            } catch (Exception e) {
                //aLista.add(e.toString());
            }
        }
    }

}