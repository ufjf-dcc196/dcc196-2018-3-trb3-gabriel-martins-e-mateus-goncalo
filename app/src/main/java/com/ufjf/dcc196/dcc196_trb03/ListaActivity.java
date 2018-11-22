package com.ufjf.dcc196.dcc196_trb03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ListaActivity extends AppCompatActivity {

    private static final int REQUEST_NOVALISTA = 1;

    private Button btnNovaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        btnNovaLista = (Button) findViewById(R.id.btn_NovaLista);
    }
}
