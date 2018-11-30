package com.ufjf.dcc196.dcc196_trb03;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItensListaActivity extends AppCompatActivity {

    private static final int REQUEST_NOVOITEM = 1;
    private static final int REQUEST_EDITLISTA = 2;

    private Button btnAddItem;
    private Button btnEditarLista;
    private TextView txtNomeLista;
    private TextView txtNomeMercado;
    private TextView txtDataLista;
    private RecyclerView rvItensLista;
    private ItemAdapter adapter;
    private ListaDbHelper dbHelper;
    private Integer registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_lista);

        dbHelper = new ListaDbHelper(getApplicationContext());

        final Bundle extras = getIntent().getExtras();
        registro = extras.getInt("registroLista");

        Lista lista = getLista();

        btnAddItem = (Button) findViewById(R.id.btn_addItem);
        btnEditarLista = (Button) findViewById(R.id.btn_editarLista);
        txtNomeLista = (TextView) findViewById(R.id.txt_nomeLista);
        txtDataLista = (TextView) findViewById(R.id.txt_dataLista);
        txtNomeMercado = (TextView) findViewById(R.id.txt_nomeMercado);
        txtNomeLista.setText("Nome: " + lista.getNome());
        txtDataLista.setText("Data: " + lista.getData());
        txtNomeMercado.setText("Mercado: " + lista.getNomeMercado());
        rvItensLista = (RecyclerView) findViewById(R.id.rv_itensLista);
        rvItensLista.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemAdapter(getItens());
        rvItensLista.setAdapter(adapter);

        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onListaClick(View listaView, int position) {
                
            }
        });

        adapter.setOnItemLongClickListener(new ItemAdapter.OnItemLongClickListener() {
            @Override
            public void onListaLongClick(View listaView, int position) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = AppContract.ItemLista.COLUMN_NAME_REGISTRO+" = ?";
                String [] selectArgs = {String.valueOf(position)};
                db.delete(AppContract.ItemLista.TABLE_NAME, select, selectArgs);
                adapter.setCursor(getItens());
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItensListaActivity.this, CadastrarItemActivity.class);
                intent.putExtra("registro", registro);
                startActivityForResult(intent, ItensListaActivity.REQUEST_NOVOITEM);
            }
        });

        btnEditarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItensListaActivity.this, EditarListaActivity.class);
                startActivityForResult(intent, ItensListaActivity.REQUEST_EDITLISTA);
            }
        });

    }

    private Lista getLista()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                AppContract.Lista.COLUMN_NAME_REGISTRO,
                AppContract.Lista.COLUMN_NAME_NOME,
                AppContract.Lista.COLUMN_NAME_DATA,
                AppContract.Lista.COLUMN_NAME_MERCADO,
        };
        String select = AppContract.Lista.COLUMN_NAME_REGISTRO+" = ?";
        String [] selectArgs = {String.valueOf(registro)};
        String sort = AppContract.Lista.COLUMN_NAME_NOME+ " ASC";
        Cursor cursor = db.query(AppContract.Lista.TABLE_NAME, visao,select,selectArgs,null,null, sort);
        Lista lista = new Lista();
        Integer idxNome = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_NOME);
        Integer idxData = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_DATA);
        Integer idxMercado = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_MERCADO);
        cursor.moveToPosition(0);
        lista.setNome(cursor.getString(idxNome));
        lista.setData(cursor.getString(idxData));
        lista.setNomeMercado(cursor.getString(idxMercado));
        return lista;
    }

    private Cursor getItens()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                AppContract.ItemLista.COLUMN_NAME_REGISTRO,
                AppContract.ItemLista.COLUMN_NAME_NOME,
                AppContract.ItemLista.COLUMN_NAME_QUANTIDADE,
                AppContract.ItemLista.COLUMN_NAME_VALOR,
        };
        String select = AppContract.ItemLista.COLUMN_NAME_LISTA+" = ?";
        String [] selectArgs = {String.valueOf(registro)};
        String sort = AppContract.ItemLista.COLUMN_NAME_NOME+ " ASC";
        return db.query(AppContract.ItemLista.TABLE_NAME, visao,select,selectArgs,null,null, sort);
    }
}
