package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarListaActivity extends AppCompatActivity {

    private Button btnConfNovaLista;
    private EditText etxtNomeLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_lista);

        btnConfNovaLista = (Button) findViewById(R.id.btn_ConfNovaLista);
        etxtNomeLista = (EditText) findViewById(R.id.etxt_NomeLista);

        btnConfNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
