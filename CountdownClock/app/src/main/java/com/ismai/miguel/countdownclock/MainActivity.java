package com.ismai.miguel.countdownclock;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    protected EditText seconds;
    protected Button button;
    protected AsyncGenerator task;
    protected int current;
    protected Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);
        seconds = (EditText) findViewById(R.id.seconds);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new AsyncGenerator(button, seconds);
                task.execute(Integer.parseInt(seconds.getText().toString()));
            }
        });

        if (savedInstanceState != null)
            restoreVarsFromBundle(savedInstanceState);
    }

    protected void restoreVarsFromBundle(Bundle savedInstanceState) {
        current = savedInstanceState.getInt("current");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("current", current);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            restoreVarsFromBundle(savedInstanceState);
        }
    }

    public final class AsyncGenerator extends AsyncTask<Integer, Integer, Integer> {
        protected Button button;
        protected TextView seconds;
        protected boolean ignore;

        public AsyncGenerator(Button button, TextView seconds) {
            this.button = button;
            this.seconds = seconds;
        }

        @Override
        protected void onPreExecute() {
            ignore = false;
            button.setEnabled(false);
            seconds.setEnabled(false);

            int current_s = Integer.parseInt(this.seconds.getText().toString());
            if (current_s == 0) {
                ignore = true;
                return;
            } else {
                current = current_s;
            }
        }

        @Override
        protected Integer doInBackground(Integer... args) {
            if (!ignore) {
                int n = args[0];
                if (n == 0) {
                    n = 1;
                }
                for (int k = 0; k < n; k++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        return 404;
                    }
                    current -= 1;
                    publishProgress(current);
                }
            }
            return current;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int value = values[0];
            seconds.setText("" + value);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer == 404) {
                Toast.makeText(activity, "Error: TimeUnit wasn't able to run", Toast.LENGTH_SHORT).show();
            }
            button.setEnabled(true);
            seconds.setEnabled(true);
        }
    }

}