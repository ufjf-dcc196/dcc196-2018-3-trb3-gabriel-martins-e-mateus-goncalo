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

public class CadastrarListaActivity extends AppCompatActivity {

    private Button btnConfNovaLista;
    private EditText etxtNomeLista;
    private EditText etxtDataLista;
    private EditText etxtNomeMercado;
    private ListaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_lista);

        dbHelper = new ListaDbHelper(getApplicationContext());

        btnConfNovaLista = (Button) findViewById(R.id.btn_ConfNovaLista);
        etxtNomeLista = (EditText) findViewById(R.id.etxt_NomeLista);
        etxtDataLista = (EditText) findViewById(R.id.etxt_data);
        etxtNomeMercado = (EditText) findViewById(R.id.etxt_NomeMercado);

        btnConfNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lista lista = new Lista();
                lista.setNome(etxtNomeLista.getText().toString());
                lista.setData(etxtDataLista.getText().toString());
                lista.setNomeMercado(etxtNomeMercado.getText().toString());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(AppContract.Lista.COLUMN_NAME_NOME, lista.getNome());
                valores.put(AppContract.Lista.COLUMN_NAME_DATA, lista.getData());
                valores.put(AppContract.Lista.COLUMN_NAME_EHCOMPRA, 0);
                valores.put(AppContract.Lista.COLUMN_NAME_MERCADO, lista.getNomeMercado());
                long id = db.insert(AppContract.Lista.TABLE_NAME,null, valores);
                Log.i("DBINFO", "registro criado com id: "+id);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
