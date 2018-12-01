package com.ufjf.dcc196.dcc196_trb03;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ComprasActivity extends AppCompatActivity {

    private static final int REQUEST_CADCOMPRA = 1;

    private RecyclerView rvCompras;
    private CompraAdapter adapter;
    private ListaDbHelper dbHelper;
    private Button btnCadCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        dbHelper = new ListaDbHelper(getApplicationContext());

        rvCompras = (RecyclerView) findViewById(R.id.rv_compras);
        btnCadCompras = (Button) findViewById(R.id.btn_CadCompra);

        rvCompras.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CompraAdapter(getListas());
        rvCompras.setAdapter(adapter);

        adapter.setOnCompraClickListener(new CompraAdapter.OnListaClickListener() {
            @Override
            public void onListaClick(View listaView, int position) {
                Intent intent = new Intent(ComprasActivity.this, ItensCompraActivity.class);
                Integer registroCompra = position;
                intent.putExtra("registroCompra", registroCompra);
                startActivity(intent);
            }
        });

        adapter.setOnCompraLongClickListener(new CompraAdapter.OnListaLongClickListener() {
            @Override
            public void onListaLongClick(View listaView, int position) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = AppContract.Lista.COLUMN_NAME_REGISTRO+" = ?";
                String [] selectArgs = {String.valueOf(position)};
                db.delete(AppContract.Lista.TABLE_NAME, select, selectArgs);
                adapter.setCursor(getListas());
            }
        });

        btnCadCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComprasActivity.this, CadastrarCompraActivity.class);
                startActivityForResult(intent, ComprasActivity.REQUEST_CADCOMPRA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.setCursor(getListas());
    }

    private Cursor getListas()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                AppContract.Lista.COLUMN_NAME_REGISTRO,
                AppContract.Lista.COLUMN_NAME_NOME,
                AppContract.Lista.COLUMN_NAME_DATA,
                AppContract.Lista.COLUMN_NAME_VALOR,
        };
        String select = AppContract.Lista.COLUMN_NAME_EHCOMPRA+" = ?";
        String [] selectArgs = {String.valueOf(1)};
        String sort = AppContract.Lista.COLUMN_NAME_NOME+ " ASC";
        return db.query(AppContract.Lista.TABLE_NAME, visao,select,selectArgs,null,null, sort);
    }
}
