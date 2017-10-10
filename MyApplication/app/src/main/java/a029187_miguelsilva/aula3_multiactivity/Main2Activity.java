package a029187_miguelsilva.aula3_multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends Activity {

    Button previousButton;
    TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        previousButton = findViewById(R.id.previousButton);
        nome = (TextView) findViewById(R.id.nome);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent i = getIntent();
        String n = i.getStringExtra("oNome");
        int k = i.getIntExtra("oIDs", 5896);
        nome.setText(n + ": " + k);
    }
}