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

public class EditarListaActivity extends AppCompatActivity {

    private Button btnConfEditLista;
    private EditText etxtNomeListaEd;
    private EditText etxtDataEd;
    private EditText etxtNomeMercadoEd;
    private ListaDbHelper dbHelper;
    private Integer registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista);

        dbHelper = new ListaDbHelper(getApplicationContext());

        final Bundle extras = getIntent().getExtras();
        registro = extras.getInt("registro");

        Lista lista = getLista();

        btnConfEditLista = (Button) findViewById(R.id.btn_ConfEditLista);
        etxtNomeListaEd = (EditText) findViewById(R.id.etxt_NomeListaEd);
        etxtDataEd = (EditText) findViewById(R.id.etxt_dataEd);
        etxtNomeMercadoEd = (EditText) findViewById(R.id.etxt_NomeMercadoEd);
        etxtNomeListaEd.setText(lista.getNome());
        etxtDataEd.setText(lista.getData());
        etxtNomeMercadoEd.setText(lista.getNomeMercado());

        btnConfEditLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(AppContract.Lista.COLUMN_NAME_NOME, etxtNomeListaEd.getText().toString());
                cv.put(AppContract.Lista.COLUMN_NAME_DATA, etxtDataEd.getText().toString());
                cv.put(AppContract.Lista.COLUMN_NAME_MERCADO, etxtNomeMercadoEd.getText().toString());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.update(AppContract.Lista.TABLE_NAME, cv, AppContract.Lista.COLUMN_NAME_REGISTRO +"="+registro, null);
                setResult(Activity.RESULT_OK);
                finish();
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


}
