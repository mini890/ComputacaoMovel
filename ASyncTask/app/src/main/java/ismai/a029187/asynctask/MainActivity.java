package ismai.a029187.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    protected EditText campoInputMin, campoInputMax;
    protected TextView campoOutputMed;
    protected int inputMin, inputMax;
    protected double outputMed;
    protected Random r;
    protected Button botaoGerar;
    protected int contador;
    protected double percentage;
    AsyncGenerator backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoInputMin = (EditText) findViewById(R.id.inputMinimo);
        campoInputMax = (EditText) findViewById(R.id.inputMaximo);
        campoOutputMed = (TextView) findViewById(R.id.outputMedia);
        botaoGerar = (Button) findViewById(R.id.botaoGerar);
        botaoGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundTask = new AsyncGenerator(botaoGerar, campoInputMin, campoInputMax, campoOutputMed);
                backgroundTask.execute(200000);
            }
        });
        contador = 0;
        r = new Random(); //DEBUG
        if (savedInstanceState != null) {
            restoreVarsFromBundle(savedInstanceState);
            Toast.makeText(this, "onCreate(With Bundle)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "onCreate(Null)", Toast.LENGTH_SHORT).show();
        }
    }

    protected void restoreVarsFromBundle(Bundle savedInstanceState) {
        inputMin = savedInstanceState.getInt("m´ınimo");
        if (inputMin != 0)
            campoInputMin.setText("" + inputMin);
        inputMax = savedInstanceState.getInt("m´aximo");
        if (inputMax != 0)
            campoInputMax.setText("" + inputMax);
        outputMed = savedInstanceState.getInt("m´edia");
        if (outputMed != 0)
            campoOutputMed.setText("" + outputMed);
        contador = savedInstanceState.getInt("contador");
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outputState) {
        String s = campoInputMin.getText().toString();
        if (!s.equals("")) {
            inputMin = Integer.parseInt(s);
            outputState.putInt("m´ınimo", inputMin);
        }
        s = campoInputMax.getText().toString();
        if (!s.equals("")) {
            inputMax = Integer.parseInt(s);
            outputState.putInt("m´aximo", inputMax);
        }
        s = campoOutputMed.getText().toString();
        if (!s.equals("")) {
            outputMed = Double.parseDouble(s);
            outputState.putDouble("m´edia", outputMed);
        }
        outputState.putInt("contador", contador);
        Toast.makeText(this, "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        super.onSaveInstanceState(outputState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            restoreVarsFromBundle(savedInstanceState);
        }
        Toast.makeText(this, "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
    }

    public double gerarNumeros(int n, int min, int max, double media) {
        double d = media;
        for (int k = 0; k < n; ++k)
            d = atualizarNumeros(min, max, d);
        return d;
    }

    public double atualizarNumeros(int min, int max, double media) {
        int largura = max - min;
        int base = min;
        if (largura < 0) {
            largura = -largura;
            base = max;
        }
        ++largura;
        int oNovo = (int) (r.nextDouble() * largura) + base;
        media = (media * contador + oNovo) / (contador + 1);
        ++contador;
        return media;
    }

    public final class AsyncGenerator extends AsyncTask<Integer, Double, Double> {
        protected Button b;
        protected String savedLabel;
        protected TextView tMin, tMax, tMedia;
        protected int min, max;
        protected double media;
        protected boolean ignorar;

        public AsyncGenerator(Button botao, TextView min, TextView max, TextView media) {
            b = botao;
            tMedia = media;
            tMin = min;
            tMax = max;
        }

        @Override // runs on the GUI thread
        protected void onPreExecute() {
            ignorar = false;
            savedLabel = b.getText().toString();
            b.setText("0%");
            b.setEnabled(false);
            String s = tMedia.getText().toString();
            if (s.equals(""))
                media = 0.0;
            else
                media = Double.parseDouble(s);
            s = tMin.getText().toString();
            if (s.equals("")) {
                ignorar = true;
                return;
            }
            min = Integer.parseInt(s);
            s = tMax.getText().toString();
            if (s.equals("")) {
                ignorar = true;
                return;
            }
            max = Integer.parseInt(s);
        }


        @Override
        protected Double doInBackground(Integer... args) {
            if (!ignorar) {
                int n = args[0] / 20;
                if (n == 0)
                    n = 1;
                for (int k = 0; k < 20; ++k) {
                    media = gerarNumeros(n, min, max, media);
                    publishProgress(k / 20.0);
                }
            }
            return media;
        }

        @Override // runs on the GUI thread
        protected void onProgressUpdate(Double... percentComplete) {
            String theText;
            percentage = percentComplete[0];
            theText = "" + (int) (percentage * 100) + "%";
            b.setText(theText);
        }

        @Override // runs on the GUI thread
        protected void onPostExecute(Double d) {
            b.setText(savedLabel);
            b.setEnabled(true);
            tMedia.setText("" + d);
        }
    }

}