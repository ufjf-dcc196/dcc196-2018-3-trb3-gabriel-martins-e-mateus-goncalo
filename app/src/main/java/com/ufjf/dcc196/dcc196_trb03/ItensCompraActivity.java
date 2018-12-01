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
import android.widget.TextView;

public class ItensCompraActivity extends AppCompatActivity {

    private static final int REQUEST_NOVOITEM = 1;
    private static final int REQUEST_ITEMCOMPRA = 1;
    private static final int REQUEST_EDITCOMPRA = 1;

    private Button btnEditarCompra;
    private TextView txtNomeCompra;
    private TextView txtNomeMercado;
    private TextView txtDataCompra;
    private TextView txtValorCompra;
    private Button btnAddItemCompra;
    private RecyclerView rvItensCompra;
    private ItemAdapter adapter;
    private ListaDbHelper dbHelper;
    private Integer registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intens_compra);

        dbHelper = new ListaDbHelper(getApplicationContext());

        final Bundle extras = getIntent().getExtras();
        registro = extras.getInt("registroCompra");

        btnAddItemCompra = (Button) findViewById(R.id.btn_addItemCompra);
        btnEditarCompra = (Button) findViewById(R.id.btn_editarCompra);
        txtNomeCompra = (TextView) findViewById(R.id.txt_nomeLista2);
        txtDataCompra = (TextView) findViewById(R.id.txt_dataLista2);
        txtNomeMercado = (TextView) findViewById(R.id.txt_nomeMercado2);
        txtValorCompra = (TextView) findViewById(R.id.txt_Valor2);

        preencherInformacoesCompra();
        adapter = new ItemAdapter(getItens());

        rvItensCompra = (RecyclerView) findViewById(R.id.rv_itensCompra);
        rvItensCompra.setLayoutManager(new LinearLayoutManager(this));
        rvItensCompra.setAdapter(adapter);

        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onListaClick(View listaView, int position) {
                Intent intent = new Intent(ItensCompraActivity.this, VisualizarItemActivity.class);
                intent.putExtra("registro", position);
                startActivityForResult(intent, ItensCompraActivity.REQUEST_NOVOITEM);
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

        btnEditarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItensCompraActivity.this, EditarCompraActivity.class);
                intent.putExtra("registro", registro);
                startActivityForResult(intent, ItensCompraActivity.REQUEST_EDITCOMPRA);
            }
        });

        btnAddItemCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItensCompraActivity.this, CadastrarItemActivity.class);
                intent.putExtra("registro", registro);
                startActivityForResult(intent, ItensCompraActivity.REQUEST_ITEMCOMPRA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.setCursor(getItens());
        preencherInformacoesCompra();
    }

    private void preencherInformacoesCompra() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                AppContract.Lista.COLUMN_NAME_REGISTRO,
                AppContract.Lista.COLUMN_NAME_NOME,
                AppContract.Lista.COLUMN_NAME_DATA,
                AppContract.Lista.COLUMN_NAME_MERCADO,
                AppContract.Lista.COLUMN_NAME_VALOR,
        };
        String select = AppContract.Lista.COLUMN_NAME_REGISTRO+" = ?";
        String [] selectArgs = {String.valueOf(registro)};
        String sort = AppContract.Lista.COLUMN_NAME_NOME+ " ASC";
        Cursor cursor = db.query(AppContract.Lista.TABLE_NAME, visao,select,selectArgs,null,null, sort);
        Lista lista = new Lista();
        Integer idxNome = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_NOME);
        Integer idxData = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_DATA);
        Integer idxMercado = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_MERCADO);
        Integer idxValor = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_VALOR);
        cursor.moveToPosition(0);
        lista.setNome(cursor.getString(idxNome));
        lista.setData(cursor.getString(idxData));
        lista.setNomeMercado(cursor.getString(idxMercado));
        lista.setValor(cursor.getDouble(idxValor));
        txtNomeCompra.setText("Nome: " + lista.getNome());
        txtDataCompra.setText("Data: " + lista.getData());
        txtNomeMercado.setText("Mercado: " + lista.getNomeMercado());
        txtValorCompra.setText("Valor: R$" + String.valueOf(lista.getValor()));
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
