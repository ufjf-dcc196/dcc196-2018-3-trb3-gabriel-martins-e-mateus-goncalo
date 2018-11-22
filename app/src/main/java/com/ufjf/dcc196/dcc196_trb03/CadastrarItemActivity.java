package com.ufjf.dcc196.dcc196_trb03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarItemActivity extends AppCompatActivity {

    private Button btnConfNovoItem;
    private EditText etxtNomeItem;
    private EditText etxtQuantidadeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item);

        btnConfNovoItem = (Button) findViewById(R.id.btn_ConfNovoItem);
        etxtNomeItem = (EditText) findViewById(R.id.etxt_NomeItem);
        etxtQuantidadeItem = (EditText) findViewById(R.id.etxt_QtdItem);
    }
}
