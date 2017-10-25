package miguelsilva.basededados;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

public class ListarActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }
}
