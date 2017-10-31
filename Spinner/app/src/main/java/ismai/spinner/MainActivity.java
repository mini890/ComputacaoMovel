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
    protected List<String> moradas, telefones;
    int index;
    List<String> nomes;
    Activity activity;

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
        nomes.add("NOVO");
        moradas.add("NOVO");
        telefones.add("NOVO");

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

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    nomes.add(nome.getText().toString());
                    moradas.add(morada.getText().toString());
                    telefones.add(telefone.getText().toString());
                } else {
                    nomes.set(index, nome.getText().toString());
                    moradas.set(index, morada.getText().toString());
                    telefones.set(index, telefone.getText().toString());
                }

                ArrayAdapter<String> oAdaptador2 = new ArrayAdapter<String>(activity,
                        android.R.layout.simple_spinner_item, nomes);
                oAdaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                escolher.setAdapter(oAdaptador2);
            }
        });
    }
}