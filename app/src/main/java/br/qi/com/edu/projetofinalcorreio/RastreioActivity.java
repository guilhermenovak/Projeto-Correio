package br.qi.com.edu.projetofinalcorreio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RastreioActivity extends AppCompatActivity {
    TextView txtResposta;
    Button btnRastrear, btnVisualizar, btnCadastrarRastreio, btnStatus, btnPacote;
    private ImageView viewMaps;
    private CorreioDAO objCorreioDAO;
    private List<Correio> todosOsPacotes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreio);
        getSupportActionBar().hide();

        objCorreioDAO = new CorreioDAO(this);

        txtResposta = findViewById(R.id.txtResposta);
        btnRastrear = findViewById(R.id.btnRastrear);
        btnVisualizar = findViewById(R.id.btnVisualizar);
        btnCadastrarRastreio = findViewById(R.id.btnCadastrarRastreio);
        btnStatus = findViewById(R.id.btnStatus);
        btnPacote = findViewById(R.id.btnPacote);
        viewMaps = findViewById(R.id.viewMaps);





        btnCadastrarRastreio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RastreioActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RastreioActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

        btnPacote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RastreioActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.correios.com.br/"));
                startActivity(intent);
            }
        });

        viewMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.br/maps/"));
                startActivity(intent);
            }
        });

        btnRastrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.correios.com.br/"));
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        rastreio();
        txtResposta.setText("Nome: "+todosOsPacotes.get(todosOsPacotes.size()-1).getNome()+"\n"+"Código: "+todosOsPacotes.get(todosOsPacotes.size()-1).getCodigo());

    }

    private void rastreio() {
        todosOsPacotes = objCorreioDAO.consultarPacotesBD();
        int lastIdx = todosOsPacotes.size() -1;
    }
}