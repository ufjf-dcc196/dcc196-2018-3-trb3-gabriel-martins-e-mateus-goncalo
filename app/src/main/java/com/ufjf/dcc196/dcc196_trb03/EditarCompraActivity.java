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
import android.widget.Toast;

public class EditarCompraActivity extends AppCompatActivity {

    private Button btnConfEditCompra;
    private EditText etxtNomeCompraEd;
    private EditText etxtDataCompraEd;
    private EditText etxtNomeMercadoCompra;
    private EditText etxtValorCompra;
    private ListaDbHelper dbHelper;
    private Integer registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_compra);
        btnConfEditCompra = (Button) findViewById(R.id.btn_ConfEditCompra);
        etxtNomeCompraEd = (EditText)findViewById(R.id.etxt_NomeCompraEd);
        etxtDataCompraEd = (EditText)findViewById(R.id.etxt_dataCompraEd);
        etxtNomeMercadoCompra = (EditText)findViewById(R.id.etxt_NomeMercadoCompraEd);
        etxtValorCompra = (EditText)findViewById(R.id.etxt_ValorCompraEd);

        dbHelper = new ListaDbHelper(getApplicationContext());

        final Bundle extras = getIntent().getExtras();
        registro = extras.getInt("registro");

        Lista lista = getLista();

        etxtNomeCompraEd.setText(lista.getNome());
        etxtDataCompraEd.setText(lista.getData());
        etxtNomeMercadoCompra.setText(lista.getNomeMercado());
        etxtValorCompra.setText(String.valueOf(lista.getValor()));

        btnConfEditCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etxtNomeCompraEd.getText().toString().isEmpty()) {
                    ContentValues cv = new ContentValues();
                    cv.put(AppContract.Lista.COLUMN_NAME_NOME, etxtNomeCompraEd.getText().toString());
                    cv.put(AppContract.Lista.COLUMN_NAME_DATA, etxtDataCompraEd.getText().toString());
                    cv.put(AppContract.Lista.COLUMN_NAME_MERCADO, etxtNomeMercadoCompra.getText().toString());
                    cv.put(AppContract.Lista.COLUMN_NAME_VALOR, Double.parseDouble(etxtValorCompra.getText().toString()));
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.update(AppContract.Lista.TABLE_NAME, cv, AppContract.Lista.COLUMN_NAME_REGISTRO + "=" + registro, null);
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

    private Lista getLista()
    {
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
        return lista;
    }
}
