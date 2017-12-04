package pt.ismai.a029187.xmlparser;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.StringReader;
import java.util.List;

public class Customer extends Activity {
    protected AsyncGenerator backgroundTask;
    protected Intent intent;
    protected String host, path;
    protected List<Cliente> clienteList;
    protected Cliente cliente;
    protected TextView id_text, firstname, lastname, street, city;
    private int id, port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        id_text = findViewById(R.id.id);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);

        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        backgroundTask = new AsyncGenerator("thomas-bayer.com", "/sqlrest/CUSTOMER", 80, id, firstname);
    }

    @Override
    protected void onStart() {
        super.onStart();
        backgroundTask.execute();
    }

    private class AsyncGenerator extends AsyncTask<String, Void, String> {
        protected String host, path;
        protected int port, id;
        protected TextView firstname;

        AsyncGenerator(String host, String path, int port, int id, TextView firstname) {
            this.host = host;
            this.path = path;
            this.port = port;
            this.id = id;
            this.firstname = firstname;
        }

        @Override
        protected String doInBackground(String... strings) {
            return Comunicar.contactar2(host, path + "/" + id, 80);
        }

        @Override
        protected void onPostExecute(String s) {
            clienteList = null;
            try {
                SaxXmlParser<Cliente, SaxCustomerIdXmlHandler> parser = new SaxXmlParser<Cliente, SaxCustomerIdXmlHandler>();
                parser.setHandler(new SaxCustomerIdXmlHandler());
                clienteList = parser.parse(new StringReader(s));
                id_text.setText("" + clienteList.get(0).getId());
                firstname.setText("" + clienteList.get(0).getFirstName());
                lastname.setText("" + clienteList.get(0).getLastName());
                street.setText("" + clienteList.get(0).getStreet());
                city.setText("" + clienteList.get(0).getCity());
            } catch (Exception e) {
            }
        }
    }
}
