package com.example.migue.exercicioaula2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected EditText milhasIntroduzidas;
    protected TextView resultadoText;
    protected Button milhasMaritimas, milhasTerrestres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        milhasIntroduzidas = (EditText) findViewById(R.id.milhasIntroduzidas);
        resultadoText = (TextView) findViewById(R.id.resultado);
        milhasMaritimas = (Button) findViewById(R.id.button_maritima);
        milhasTerrestres = (Button) findViewById(R.id.button_terrestre);

        milhasMaritimas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resultado = Integer.parseInt(milhasIntroduzidas.getText().toString()) * 1852;
                resultadoText.setText(resultado + " metros");
                Toast toast = Toast.makeText(MainActivity.this, resultado + " metros", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        milhasTerrestres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resultado = Integer.parseInt(milhasIntroduzidas.getText().toString()) * 1609;
                resultadoText.setText(resultado + " metros");
                Toast toast = Toast.makeText(MainActivity.this, resultado + " metros", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}