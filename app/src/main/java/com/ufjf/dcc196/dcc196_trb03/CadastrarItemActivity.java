package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarItemActivity extends AppCompatActivity {

    private Button btnConfNovoItem;
    private EditText etxtNomeItem;
    private EditText etxtQuantidadeItem;
    private EditText etxtValorItem;
    private ListaDbHelper dbHelper;
    private Integer registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item);

        dbHelper = new ListaDbHelper(getApplicationContext());

        final Bundle extras = getIntent().getExtras();
        registro = extras.getInt("registro");

        btnConfNovoItem = (Button) findViewById(R.id.btn_ConfNovoItem);
        etxtNomeItem = (EditText) findViewById(R.id.etxt_NomeItem);
        etxtQuantidadeItem = (EditText) findViewById(R.id.etxt_QtdItem);
        etxtValorItem = (EditText) findViewById(R.id.etxt_ValorItem);

        btnConfNovoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etxtNomeItem.getText().toString().isEmpty()) {
                    ItemDeLista item = new ItemDeLista();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    item.setNomeItem(etxtNomeItem.getText().toString());
                    item.setQuantidadeItem(Integer.parseInt(etxtQuantidadeItem.getText().toString()));
                    if (etxtValorItem.getText().toString() != "") {
                        item.setValor(item.getQuantidadeItem() * Double.parseDouble(etxtValorItem.getText().toString()));
                        valores.put(AppContract.ItemLista.COLUMN_NAME_VALOR, item.getValor());
                    }
                    valores.put(AppContract.ItemLista.COLUMN_NAME_NOME, item.getNomeItem());
                    valores.put(AppContract.ItemLista.COLUMN_NAME_QUANTIDADE, item.getQuantidadeItem());
                    valores.put(AppContract.ItemLista.COLUMN_NAME_LISTA, registro);
                    long id = db.insert(AppContract.ItemLista.TABLE_NAME, null, valores);
                    Log.i("DBINFO", "registro criado com id: " + id);
                    setResult(Activity.RESULT_OK);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "É preciso informar pelo menos o nome", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
