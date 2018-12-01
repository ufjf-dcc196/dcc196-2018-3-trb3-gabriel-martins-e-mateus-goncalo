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

public class CadastrarCompraActivity extends AppCompatActivity {

    private Button btnConfNovaCompra;
    private EditText etxtNomeCompra;
    private EditText etxtDataCompra;
    private EditText etxtNomeMercadoCompra;
    private EditText etxtValorCompra;
    private ListaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_compra);

        dbHelper = new ListaDbHelper(getApplicationContext());

        btnConfNovaCompra = (Button) findViewById(R.id.btn_ConfNovaCompra);
        etxtNomeCompra = (EditText) findViewById(R.id.etxt_NomeCompra);
        etxtDataCompra = (EditText) findViewById(R.id.etxt_DataCompra);
        etxtValorCompra = (EditText) findViewById(R.id.etxt_ValorCompra);
        etxtNomeMercadoCompra = (EditText) findViewById(R.id.etxt_NomeMercadoCompra);

        btnConfNovaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lista lista = new Lista();
                if (!etxtNomeCompra.getText().toString().isEmpty()) {
                    lista.setNome(etxtNomeCompra.getText().toString());
                    lista.setData(etxtDataCompra.getText().toString());
                    lista.setNomeMercado(etxtNomeMercadoCompra.getText().toString());
                    lista.setValor(Double.parseDouble(etxtValorCompra.getText().toString()));
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(AppContract.Lista.COLUMN_NAME_NOME, lista.getNome());
                    valores.put(AppContract.Lista.COLUMN_NAME_DATA, lista.getData());
                    valores.put(AppContract.Lista.COLUMN_NAME_EHCOMPRA, 1);
                    valores.put(AppContract.Lista.COLUMN_NAME_VALOR, lista.getValor());
                    valores.put(AppContract.Lista.COLUMN_NAME_MERCADO, lista.getNomeMercado());
                    long id = db.insert(AppContract.Lista.TABLE_NAME, null, valores);
                    Log.i("DBINFO", "registro criado com id: " + id);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "É preciso informar pelo menos o nome", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
