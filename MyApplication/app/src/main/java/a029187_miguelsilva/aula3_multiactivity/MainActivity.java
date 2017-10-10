package a029187_miguelsilva.aula3_multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button nextButton;
    Button closeButton;
    TextView nome;

    private void changeScreen(Class<?> subActivity, String nome, int id) {
        Intent x = new Intent(this, subActivity);
        x.putExtra("oNome", nome);
        x.putExtra("oID", id);
        startActivity(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = (Button) findViewById(R.id.nextButton);
        closeButton = (Button) findViewById(R.id.closeButton);
        nome = (TextView) findViewById(R.id.nome);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScreen(Main2Activity.class, nome.getText().toString(), 2);
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}