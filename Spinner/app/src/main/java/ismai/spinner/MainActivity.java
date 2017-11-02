package ismai.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    protected EditText nome, morada, telefone;
    protected Spinner escolher;
    protected Button ok;
    protected List<String> nomes, moradas, telefones;
    protected List<Integer> ids;
    protected int index;
    protected Activity activity;
    protected AdaptadorBaseDados baseDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        nome = (EditText) findViewById(R.id.nome);
        morada = (EditText) findViewById(R.id.morada);
        telefone = (EditText) findViewById(R.id.telefone);
        escolher = (Spinner) findViewById(R.id.escolher);
        ok = (Button) findViewById(R.id.button);

        nomes = new ArrayList<String>();
        moradas = new ArrayList<String>();
        telefones = new ArrayList<String>();
        ids = new ArrayList<Integer>();
        nomes.add("NOVO");
        moradas.add("NOVO");
        telefones.add("112");

        ArrayAdapter<String> oAdaptador2 = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, nomes);
        oAdaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        escolher.setAdapter(oAdaptador2);
        escolher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                nome.setText(nomes.get(position));
                morada.setText(moradas.get(position));
                telefone.setText(telefones.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        baseDados = new AdaptadorBaseDados(this).open();
        baseDados.obterTodosCampos(ids, nomes, moradas, telefones);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    nomes.add(nome.getText().toString());
                    moradas.add(morada.getText().toString());
                    telefones.add(telefone.getText().toString());
                    baseDados.insertNomeMoradaTelefone(nomes.get(nomes.size() - 1).toString(), moradas.get(moradas.size() - 1).toString(), telefones.get(telefones.size() - 1).toString());
                } else {
                    nomes.set(index, nome.getText().toString());
                    moradas.set(index, morada.getText().toString());
                    telefones.set(index, telefone.getText().toString());
                    baseDados.updateDados(index - 1, telefones.get(index - 1).toString(), moradas.get(index - 1).toString(), telefones.get(index - 1).toString());
                }

                ArrayAdapter<String> oAdaptador2 = new ArrayAdapter<String>(activity,
                        android.R.layout.simple_spinner_item, nomes);
                oAdaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                escolher.setAdapter(oAdaptador2);
            }
        });
    }
}