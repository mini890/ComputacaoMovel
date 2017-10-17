package a029187_miguelsilva.jogodaforca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Forca forca = new Forca();

    protected TextView lifes;
    protected TextView usedLetters;
    protected EditText letterInput;
    protected Button button;
    protected TextView word;

    private boolean lostLife = true;
    private char[] currentWordCharArr;
    private int amountLifes = 6;
    private String chosenWord;
    private String currentWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifes = (TextView) findViewById(R.id.lifes);
        lifes.setText("Vidas: " + amountLifes);
        usedLetters = (TextView) findViewById(R.id.usedLetters);
        letterInput = (EditText) findViewById(R.id.letterInput);
        button = (Button) findViewById(R.id.button);
        word = (TextView) findViewById(R.id.word);

        button.setText("Jogar");

        chosenWord = forca.randomWord();
        currentWordCharArr = new char[chosenWord.length()];

        turnToDashes();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterateWord();
                lostLife();
                verifyEndGame();
            }
        });

    }

    private void turnToDashes() {
        System.out.println("Debug..." + chosenWord);
        for (int i = 0; i < chosenWord.length(); i++) {
            currentWordCharArr[i] = '-';
            word.setText(word.getText() + "-");
            currentWord = (String) word.getText();
        }
    }

    private void iterateWord() {
        for (int i = 0; i < currentWordCharArr.length; i++) {
            String inserir = letterInput.getText().toString();
            String character = String.valueOf(chosenWord.charAt(i));
            verifyLetter(inserir, character, i);
        }
        usedLetters.setText(usedLetters.getText().toString() + " " + letterInput.getText().toString());
        letterInput.setText("");
    }

    private void verifyLetter(String inserir, String character, int iterator) {
        if (inserir.equalsIgnoreCase(character)) {
            lostLife = false;
            char[] palavra_atual_chars = currentWord.toCharArray();
            palavra_atual_chars[iterator] = inserir.charAt(0);
            currentWord = String.valueOf(palavra_atual_chars);
            word.setText(currentWord);
        }
    }

    private void lostLife() {
        if (lostLife)
            amountLifes--;
        else
            lostLife = true;

        lifes.setText("Vidas: " + amountLifes);
    }

    private void verifyEndGame() {
        if (currentWord.equalsIgnoreCase(chosenWord) || amountLifes <= 0) {
            if (amountLifes <= 0) {
                word.setText("Perdeste! A word era " + chosenWord.toUpperCase());
            }
            button.setText("Reiniciar");
            letterInput.setText("");
            letterInput.setEnabled(false);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restartApp();
                }
            });
        }
    }

    private void restartApp() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}