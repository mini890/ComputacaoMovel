package a031127.jogoforcapoupado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    String[] Palavras = {"ALUGUEL", "AMAZONA", "AVO", "AZUL", "BARBA", "BARRIGA", "CHOCOLATE", "CRIANÇA", "DISCAR", "DOENTE", "DRAGAO", "FILHA", "GAIVOTA", "GATO", "JUIZ", "KIWI", "LARANJA", "LEITE", "NEGRO", "OCULOS", "OLIVA", "OUTONO", "PAO", "PEDRA", "PERMANENTE", "POTE", "QUADRO", "QUATRO", "RINOCERONTE", "SEIS", "SOFA", "SOL", "TESOURA", "TOALHA", "TRAMPOLIM", "TROMBONE", "VENTO"};
    private int vidas = 6;
    private int indice;
    private TextView letrasUsadas;
    private TextView palavraRandom;
    private TextView mensagemFinal;
    private Button jogar;
    private EditText tentarPalavra;
    private TextView vidasRestantes;
    private boolean manterVida = true;
    private String palavraEscondida;
    private boolean fimDoJogo = false;

    private void perderVida() {


        vidas--;

    }

    private void escolherPalavra() {

        indice = (int) (Math.random() * Palavras.length);


    }

    private void checarPalavra(StringBuilder palavraEscolhida, StringBuilder palavraEscondida) {

        for (int i = 0; i < palavraEscondida.length(); i++) {
            if (tentarPalavra.getText().toString().equalsIgnoreCase(String.valueOf(palavraEscolhida.charAt(i)))) {
                manterVida = false;

                System.out.println("DEBUG...... Entra");
                //palavraEscondida.setCharAt(i, tentarPalavra.getText().toString().charAt(0));
                palavraEscondida.replace(i, i + 1, tentarPalavra.getText().toString());
                System.out.println("DEBUG..... " + palavraEscolhida + " " + palavraEscondida);
                palavraRandom.setText(palavraEscondida);
            }
        }

        if (manterVida) {
            perderVida();
            manterVida = false;
        }

        if (palavraEscolhida.toString().equalsIgnoreCase(palavraEscondida.toString())) {
            fimDoJogo = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jogar = (Button) findViewById(R.id.jogar);
        letrasUsadas = (TextView) findViewById(R.id.letrasUsadas);
        palavraRandom = (TextView) findViewById(R.id.palavraRandom);
        mensagemFinal = (TextView) findViewById(R.id.mensagemFinal);
        tentarPalavra = (EditText) findViewById(R.id.tentarPalavra);
        vidasRestantes = (TextView) findViewById(R.id.vidasRestantes);

        vidasRestantes.setText("Vidas Restantes: " + vidas);

        escolherPalavra();

        final StringBuilder palavraEscolhida = new StringBuilder(Palavras[indice]);
        final StringBuilder palavraEscondida = new StringBuilder(palavraEscolhida);

        for (int i = 0; i < palavraEscondida.length(); i++) {
            System.out.println("DEBUG..... " + palavraEscolhida + " " + palavraEscondida);
            palavraEscondida.replace(i, i + 1, "-");
            System.out.println("DEBUG..... " + palavraEscolhida + " " + palavraEscondida);
        }

        palavraRandom.setText(palavraEscondida);


        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manterVida = true;

                letrasUsadas.setText(letrasUsadas.getText().toString() + " " + tentarPalavra.getText().toString() + " ");
                checarPalavra(palavraEscolhida, palavraEscondida);
                vidasRestantes.setText("Vidas Restantes " + vidas);

                tentarPalavra.setText("");

                if (vidas <= 0 || fimDoJogo) {
                    jogar.setText("Reiniciar");
                    if (vidas <= 0) {
                        mensagemFinal.setText("Perdeste! A palavra era " + palavraEscolhida);
                    } else {
                        mensagemFinal.setText("Ganhaste!");
                    }
                    jogar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent reiniciar = getIntent();
                            finish();
                            startActivity(reiniciar);
                        }
                    });
                }

            }
        });


    }
}