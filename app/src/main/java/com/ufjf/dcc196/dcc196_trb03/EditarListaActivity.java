package com.ufjf.dcc196.dcc196_trb03;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista);
        btnConfEditLista = (Button) findViewById(R.id.btn_ConfEditLista);
        etxtNomeListaEd = (EditText) findViewById(R.id.etxt_NomeListaEd);
        etxtDataEd = (EditText) findViewById(R.id.etxt_dataEd);
        etxtNomeMercadoEd = (EditText) findViewById(R.id.etxt_NomeMercadoEd);

        btnConfEditLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }


}
