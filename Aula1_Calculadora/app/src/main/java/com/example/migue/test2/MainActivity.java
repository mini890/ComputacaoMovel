package com.example.migue.test2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    protected Button botao_calcular;
    protected TextView resultado;
    protected EditText dados1, dados2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao_calcular = (Button) findViewById(R.id.botao_calcular);
        resultado = (TextView) findViewById(R.id.resultado);
        dados1 = (EditText) findViewById(R.id.editText);
        dados2 = (EditText) findViewById(R.id.editText2);
        botao_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = Integer.parseInt(dados1.getText().toString());
                int y = Integer.parseInt(dados2.getText().toString());
                int r = x * y;
                resultado.setText("" + r);
            }
        });
    }
}