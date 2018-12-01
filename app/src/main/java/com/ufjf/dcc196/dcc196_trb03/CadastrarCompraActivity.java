package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarCompraActivity extends AppCompatActivity {

    private Button btnConfNovaCompra;
    private EditText etxtNomeCompra;
    private EditText etxtDataCompra;
    private EditText etxtNomeMercadoCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_compra);

        btnConfNovaCompra = (Button) findViewById(R.id.btn_ConfNovaCompra);
        etxtNomeCompra = (EditText) findViewById(R.id.etxt_NomeCompra);
        etxtDataCompra = (EditText) findViewById(R.id.etxt_DataCompra);
        etxtNomeMercadoCompra = (EditText) findViewById(R.id.etxt_NomeMercadoCompra);

        btnConfNovaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
