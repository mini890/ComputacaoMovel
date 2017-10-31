package miguelsilva.basededados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    protected EditText nome_edit, morada_edit, telefone_edit;
    protected Button buttonInserir, buttonProximo;
    protected AdaptadorBaseDados a;
    protected List<Integer> osIds;
    protected List<String> osNomes, osMoradas, osTelefones;

    protected HashMap<String, Integer> indexes;

    private void executarOutraActivity(Class<?> subActividade, ArrayList<String> nomes) {
        Intent intent = new Intent(this, subActividade);
        intent.putExtra("osNomes", nomes);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome_edit = (EditText) findViewById(R.id.nome_edit);
        morada_edit = (EditText) findViewById(R.id.morada_edit);
        telefone_edit = (EditText) findViewById(R.id.telefone_edit);
        buttonInserir = (Button) findViewById(R.id.inserir_button);
        buttonProximo = (Button) findViewById(R.id.proximo_button);

        buttonInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nome_edit.getText().toString().equalsIgnoreCase("") || morada_edit.getText().toString().equalsIgnoreCase("") || telefone_edit.getText().toString().equalsIgnoreCase("")) {
                    //Tentei adicionar um TextChangedListener mas não tinha a certeza se podia usar outros elementos para além dos lecionados
                    Toast.makeText(MainActivity.this, "Por favor insira todos os campos para continuar", Toast.LENGTH_SHORT).show();
                } else {
                    a.insertNomeMoradaTelefone(nome_edit.getText().toString(), morada_edit.getText().toString(), telefone_edit.getText().toString());
                    Toast.makeText(MainActivity.this, "Inserido " + nome_edit.getText().toString(), Toast.LENGTH_SHORT).show();
                    nome_edit.setText("");
                    morada_edit.setText("");
                    telefone_edit.setText("");
                }
            }
        });

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executarOutraActivity(ListarActivity.class, (ArrayList) osNomes);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        a = new AdaptadorBaseDados(this).open();

        osIds = new ArrayList<Integer>();
        osNomes = new ArrayList<String>();
        osMoradas = new ArrayList<String>();
        osTelefones = new ArrayList<String>();
        a.obterTodosCampos(osIds, osNomes, osMoradas, osTelefones);
    }

    @Override
    protected void onStop() {
        super.onStop();
        a.close();
    }
}
