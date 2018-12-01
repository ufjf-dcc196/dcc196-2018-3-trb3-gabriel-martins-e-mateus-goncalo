package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarItemCompraActivity extends AppCompatActivity {

    private Button btnConfNovoItemCompra;
    private EditText etxtNomeItemCompra;
    private EditText etxtQuantidadeCompra;
    private EditText etxtValorItemCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item_compra);

        btnConfNovoItemCompra = (Button) findViewById(R.id.btn_ConfNovoItemCompra);
        etxtNomeItemCompra = (EditText) findViewById(R.id.etxt_NomeItemCompra);
        etxtQuantidadeCompra = (EditText) findViewById(R.id.etxt_QtdItemCompra);
        etxtValorItemCompra = (EditText) findViewById(R.id.etxt_ValorItemCompra);

        btnConfNovoItemCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
