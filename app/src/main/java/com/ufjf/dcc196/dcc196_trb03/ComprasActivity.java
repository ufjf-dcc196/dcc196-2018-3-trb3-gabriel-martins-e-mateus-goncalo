package com.ufjf.dcc196.dcc196_trb03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ComprasActivity extends AppCompatActivity {

    private static final int REQUEST_CADCOMPRA = 1;

    private RecyclerView rvCompras;
    private ListaAdapter adapter;
    private ListaDbHelper dbHelper;
    private Button btnCadCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        rvCompras = (RecyclerView) findViewById(R.id.rv_compras);
        btnCadCompras = (Button) findViewById(R.id.btn_CadCompra);

        btnCadCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComprasActivity.this, CadastrarCompraActivity.class);
                startActivityForResult(intent, ComprasActivity.REQUEST_CADCOMPRA);
            }
        });
    }
}
