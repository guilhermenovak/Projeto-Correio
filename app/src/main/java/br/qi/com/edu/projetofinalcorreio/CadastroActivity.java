package br.qi.com.edu.projetofinalcorreio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    EditText edtNome, edtCodigo;
    Button btnCadastrar, btnListar;
    private CorreioDAO objCorreioDAO;
    private Correio objCorreio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.edtNome);
        edtCodigo = findViewById(R.id.edtCodigo);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnListar = findViewById(R.id.btnListar);
        objCorreioDAO = new CorreioDAO(this);

        Intent i = getIntent();
        if (i.hasExtra("pacote")){
            objCorreio = (Correio) i.getSerializableExtra("pacote");

            edtNome.setText(objCorreio.getNome());
            edtCodigo.setText(String.valueOf(objCorreio.getCodigo()));
            btnCadastrar.setText("Alterar");
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objCorreio == null) {
                    Correio objCorreio = new Correio();
                    objCorreio.setNome(edtNome.getText().toString());
                    objCorreio.setCodigo(Double.parseDouble(edtCodigo.getText().toString()));

                    objCorreioDAO.cadastrarPacoteBD(objCorreio);
                    Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso! ", Toast.LENGTH_LONG).show();
                }else{
                    objCorreio.setNome(edtNome.getText().toString());
                    objCorreio.setCodigo(Double.parseDouble(edtCodigo.getText().toString()));

                    objCorreioDAO.alterarPacotesBD(objCorreio);
                    Toast.makeText(CadastroActivity.this, "Alterado com sucesso! ", Toast.LENGTH_LONG).show();
                }

                edtNome.setText("");
                edtCodigo.setText("");
                edtNome.requestFocus();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CadastroActivity.this, ListaActivity.class);
                startActivity(i);
            }
        });

    }
}