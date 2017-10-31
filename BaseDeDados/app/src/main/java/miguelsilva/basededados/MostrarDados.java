package miguelsilva.basededados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MostrarDados extends Activity {

    protected AdaptadorBaseDados a;
    protected List<Integer> osIds;
    protected List<String> osNomes, osMoradas, osTelefones;
    protected TextView mostra_nome, mostra_morada, mostra_telefone;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_dados);

        mostra_nome = (TextView) findViewById(R.id.mostra_nome);
        mostra_morada = (TextView) findViewById(R.id.mostra_morada);
        mostra_telefone = (TextView) findViewById(R.id.mostra_telefone);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 1);
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

        mostra_nome.setText(mostra_nome.getText() + " " + osNomes.get(position));
        mostra_morada.setText(mostra_morada.getText() + " " + osMoradas.get(position));
        mostra_telefone.setText(mostra_telefone.getText() + " " + osTelefones.get(position));
    }

    @Override
    protected void onStop() {
        super.onStop();
        a.close();
    }
}