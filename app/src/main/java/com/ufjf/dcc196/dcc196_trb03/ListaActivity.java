package com.ufjf.dcc196.dcc196_trb03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ListaActivity extends AppCompatActivity {

    private static final int REQUEST_NOVALISTA = 1;

    private Button btnNovaLista;
    private RecyclerView rvListas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        btnNovaLista = (Button) findViewById(R.id.btn_NovaLista);
        rvListas = (RecyclerView) findViewById(R.id.rv_listas);

        btnNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, CadastrarListaActivity.class);
                startActivityForResult(intent, ListaActivity.REQUEST_NOVALISTA);
            }
        });
    }
}
