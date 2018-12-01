package com.ufjf.dcc196.dcc196_trb03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ItensCompraActivity extends AppCompatActivity {

    private static final int REQUEST_ITEMCOMPRA = 1;

    private Button btnEditarCompra;
    private Button btnAddItemCompra;
    private RecyclerView rvItensCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intens_compra);

        btnAddItemCompra = (Button) findViewById(R.id.btn_addItemCompra);
        btnEditarCompra = (Button) findViewById(R.id.btn_editarCompra);
        rvItensCompra = (RecyclerView) findViewById(R.id.rv_itensCompra);

        btnEditarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAddItemCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItensCompraActivity.this, CadastrarItemCompraActivity.class);
                startActivityForResult(intent, ItensCompraActivity.REQUEST_ITEMCOMPRA);
            }
        });
    }
}
