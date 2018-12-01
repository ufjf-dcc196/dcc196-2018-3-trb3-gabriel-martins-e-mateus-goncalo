package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarCompraActivity extends AppCompatActivity {

    private Button btnConfEditCompra;
    private EditText etxtNomeCompraEd;
    private EditText etxtDataCompraEd;
    private EditText etxtNomeMercadoCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_compra);
        btnConfEditCompra = (Button) findViewById(R.id.btn_ConfEditCompra);
        etxtNomeCompraEd = (EditText)findViewById(R.id.etxt_NomeCompraEd);
        etxtDataCompraEd = (EditText)findViewById(R.id.etxt_dataCompraEd);
        etxtNomeMercadoCompra = (EditText)findViewById(R.id.etxt_NomeMercadoCompra);

        btnConfEditCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
