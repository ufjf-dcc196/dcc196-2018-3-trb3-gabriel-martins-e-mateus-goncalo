package com.ufjf.dcc196.dcc196_trb03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LISTA = 1;
    private static final int REQUEST_COMPRA = 2;

    private Button btnListas;
    private Button btnGerenCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListas = (Button) findViewById(R.id.btn_Listas);
        btnGerenCompras = (Button) findViewById(R.id.btn_GerencCompras);

        btnListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                startActivityForResult(intent, MainActivity.REQUEST_LISTA);
            }
        });

        btnGerenCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComprasActivity.class);
                startActivityForResult(intent, MainActivity.REQUEST_COMPRA);
            }
        });
    }
}
