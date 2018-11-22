package com.ufjf.dcc196.dcc196_trb03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class ItensListaActivity extends AppCompatActivity {

    private Button btnAddItem;
    private RecyclerView rvItensLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_lista);

        btnAddItem = (Button) findViewById(R.id.btn_addItem);
        rvItensLista = (RecyclerView) findViewById(R.id.rv_itensLista);
    }
}
