package a029187_miguelsilva.aula3_exercicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText nome;
    EditText codigo;
    Button exit;
    Button next;

    private void changeScreen(Class<?> subActivity, String nome, int codigo) {
        Intent x = new Intent(this, subActivity);
        x.putExtra("nome", nome);
        x.putExtra("codigo", codigo);
        startActivity(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.nome);
        codigo = (EditText) findViewById(R.id.codigo);
        exit = (Button) findViewById(R.id.exit);
        next = (Button) findViewById(R.id.next);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(Main2Activity.class, nome.getText().toString(), Integer.parseInt(codigo.getText().toString()));
            }
        });
    }
}
