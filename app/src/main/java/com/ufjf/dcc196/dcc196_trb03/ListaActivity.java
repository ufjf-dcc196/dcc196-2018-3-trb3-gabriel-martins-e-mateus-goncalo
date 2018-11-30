package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
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

public class ListaActivity extends AppCompatActivity {

    private static final int REQUEST_NOVALISTA = 1;

    private Button btnNovaLista;
    private RecyclerView rvListas;
    private ListaAdapter adapter;
    private ListaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        dbHelper = new ListaDbHelper(getApplicationContext());

        btnNovaLista = (Button) findViewById(R.id.btn_NovaLista);
        rvListas = (RecyclerView) findViewById(R.id.rv_listas);
        rvListas.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListaAdapter(getListas());
        rvListas.setAdapter(adapter);

        adapter.setOnListaClickListener(new ListaAdapter.OnListaClickListener() {
            @Override
            public void onListaClick(View listaView, int position) {
                // ver também as informações da lista deve ser possível e editar informações
                Intent intent = new Intent(ListaActivity.this, ItensListaActivity.class);
                Integer registroLista = position;
                intent.putExtra("registroLista", registroLista);
                startActivity(intent);
            }
        });

        adapter.setOnListaLongClickListener(new ListaAdapter.OnListaLongClickListener() {
            @Override
            public void onListaLongClick(View listaView, int position) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = AppContract.Lista.COLUMN_NAME_REGISTRO+" = ?";
                String [] selectArgs = {String.valueOf(position)};
                db.delete(AppContract.Lista.TABLE_NAME, select, selectArgs);
                adapter.setCursor(getListas());
            }
        });

        btnNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, CadastrarListaActivity.class);
                startActivityForResult(intent, ListaActivity.REQUEST_NOVALISTA);
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
        };
        String select = AppContract.Lista.COLUMN_NAME_EHCOMPRA+" = ?";
        String [] selectArgs = {String.valueOf(0)};
        String sort = AppContract.Lista.COLUMN_NAME_NOME+ " ASC";
        return db.query(AppContract.Lista.TABLE_NAME, visao,select,selectArgs,null,null, sort);
    }
}
