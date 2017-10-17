package a029187_miguelsilva.aula3_exercicio;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main3Activity extends Activity {

    EditText moradaFaturacao;
    TextView informacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        moradaFaturacao = (EditText) findViewById(R.id.moradaFaturacao);
        informacao = (TextView) findViewById(R.id.informacao);

        Intent i = new Intent();
        i = getIntent();

        informacao.setText("Nome: " + i.getStringExtra("nome"));
    }
}
