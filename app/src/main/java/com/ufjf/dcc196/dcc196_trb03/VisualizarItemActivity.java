package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VisualizarItemActivity extends AppCompatActivity {

    private EditText txtNomeItem;
    private EditText txtValorItem;
    private EditText txtQuantidadeItem;
    private Button btnConfirmarEditar;
    private ListaDbHelper dbHelper;
    private Integer registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_item);

        dbHelper = new ListaDbHelper(getApplicationContext());

        final Bundle extras = getIntent().getExtras();
        registro = extras.getInt("registro");

        txtNomeItem = (EditText) findViewById(R.id.etxt_VNomeItem);
        txtQuantidadeItem = (EditText) findViewById(R.id.etxt_VQuantidadeItem);
        txtValorItem = (EditText) findViewById(R.id.etxt_VValorItem);
        btnConfirmarEditar = (Button) findViewById(R.id.editarItem);

        preencherItem();

        btnConfirmarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(AppContract.ItemLista.COLUMN_NAME_NOME, txtNomeItem.getText().toString());
                cv.put(AppContract.ItemLista.COLUMN_NAME_QUANTIDADE, txtQuantidadeItem.getText().toString());
                if (txtValorItem.getText().toString() != "")
                {
                    cv.put(AppContract.ItemLista.COLUMN_NAME_VALOR, Integer.parseInt(txtQuantidadeItem.getText().toString()) * Double.parseDouble(txtValorItem.getText().toString()));
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.update(AppContract.ItemLista.TABLE_NAME, cv, AppContract.Lista.COLUMN_NAME_REGISTRO +"="+registro, null);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    private void preencherItem()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                AppContract.ItemLista.COLUMN_NAME_REGISTRO,
                AppContract.ItemLista.COLUMN_NAME_NOME,
                AppContract.ItemLista.COLUMN_NAME_QUANTIDADE,
                AppContract.ItemLista.COLUMN_NAME_VALOR,
        };
        String select = AppContract.ItemLista.COLUMN_NAME_REGISTRO+" = ?";
        String [] selectArgs = {String.valueOf(registro)};
        String sort = AppContract.ItemLista.COLUMN_NAME_NOME+ " ASC";
        Cursor cursor = db.query(AppContract.ItemLista.TABLE_NAME, visao,select,selectArgs,null,null, sort);
        ItemDeLista item = new ItemDeLista();
        Integer idxNome = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_NOME);
        Integer idxQuantidade = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_QUANTIDADE);
        Integer idxValor = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_VALOR);
        cursor.moveToPosition(0);
        item.setNomeItem(cursor.getString(idxNome));
        item.setQuantidadeItem(cursor.getInt(idxQuantidade));
        item.setValor(cursor.getDouble(idxValor)/item.getQuantidadeItem());
        txtNomeItem.setText(item.getNomeItem());
        txtQuantidadeItem.setText(String.valueOf(item.getQuantidadeItem()));
        txtValorItem.setText(String.valueOf(item.getValor()));
    }
}
