package ismai.a029187.gethttp_tcm;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    protected Button button;
    protected TextView ver;
    protected AsyncGenerator task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        ver = findViewById(R.id.ver);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new AsyncGenerator(button, ver);
                task.execute();
            }
        });
    }

    public final class AsyncGenerator extends AsyncTask<String, Void, String> { //Background, Progresso, Resultado

        Button button;
        TextView ver;

        AsyncGenerator(Button button, TextView ver) {
            this.button = button;
            this.ver = ver;
        }

        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            return Comunicar.contactar2("www.ismai.pt", "/pt/ensino/oferta-formativa/licenciaturas/tecnologias-de-comunicacao-multimedia", 80);
        }

        @Override
        protected void onPostExecute(String s) {
            ver.setText(s);
            button.setEnabled(true);
        }
    }
}