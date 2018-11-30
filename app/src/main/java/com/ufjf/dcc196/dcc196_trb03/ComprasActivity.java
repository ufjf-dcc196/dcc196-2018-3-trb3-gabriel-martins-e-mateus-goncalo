package com.ufjf.dcc196.dcc196_trb03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ComprasActivity extends AppCompatActivity {

    private RecyclerView rvCompras;
    private ListaAdapter adapter;
    private ListaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        rvCompras = (RecyclerView) findViewById(R.id.rv_compras);
    }
}
