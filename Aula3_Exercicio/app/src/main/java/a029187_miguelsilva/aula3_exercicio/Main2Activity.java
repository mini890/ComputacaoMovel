package a029187_miguelsilva.aula3_exercicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends Activity {

    TextView info;
    EditText morada, telefone;
    Button next, back;

    private void changeScreen(Class<?> subActivity, String nome, int codigo) {
        Intent x = new Intent(this, subActivity);
        x.putExtra("nome", nome);
        x.putExtra("codigo", codigo);
        startActivity(x);
    }

    private void changeScreen(Class<?> subActivity, String nome, int codigo, String morada, int telefone) {
        Intent x = new Intent(this, subActivity);
        x.putExtra("nome", nome);
        x.putExtra("codigo", codigo);
        x.putExtra("morada", morada);
        x.putExtra("telefone", telefone);
        startActivity(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        info = (TextView) findViewById(R.id.informacao);
        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next2);
        morada = (EditText) findViewById(R.id.morada);
        telefone = (EditText) findViewById(R.id.telefone);

        final Intent i = getIntent();
        info.setText("Nome: " + i.getStringExtra("nome") + " - Codigo: " + i.getIntExtra("codigo", 565456));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(Main3Activity.class, i.getStringExtra("nome"), i.getIntExtra("codigo", 55656), morada.getText().toString(), Integer.parseInt(telefone.getText().toString()));
            }
        });
    }
}
