package br.qi.com.edu.projetofinalcorreio;

import static androidx.core.os.LocaleListCompat.create;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ListView lstPacotes;
    private CorreioDAO objCorreioDAO;
    private SearchView icConsultar;
    private List<Correio> todosOsPacotes;
    private List<Correio> pacotesFiltrados = new ArrayList<>();
    ArrayAdapter<Correio> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstPacotes = findViewById(R.id.lstPacotes);
        objCorreioDAO = new CorreioDAO(this);

        todosOsPacotes = objCorreioDAO.consultarPacotesBD();
        pacotesFiltrados.addAll(todosOsPacotes);

        adaptador= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todosOsPacotes);
        lstPacotes.setAdapter(adaptador);

        registerForContextMenu(lstPacotes);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_principal,menu);
        icConsultar = (SearchView) menu.findItem(R.id.icConsultar).getActionView();

        icConsultar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                procurarPacotesPorNome(s);
                return false;
            }
        });
        return true;
    }

    public void procurarPacotesPorNome(String nome) {
        for (int i =0;i < todosOsPacotes.size();i++){
            if (todosOsPacotes.get(i).getNome().toLowerCase().contains(nome.toLowerCase())){
                pacotesFiltrados.add(todosOsPacotes.get(i));
            }
        }
        lstPacotes.invalidateViews();
    }

    public void abrirTelaDeCadastro(MenuItem item) {
        Intent i = new Intent(ListaActivity.this, CadastroActivity.class);
        startActivity(i);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_item,menu);
    }

    public void excluirPacote(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Correio objCorreioDeletado = pacotesFiltrados.get(menuInfo.position);

        AlertDialog confirmacao = new AlertDialog
                .Builder(this)
                .setIcon(R.drawable.ic_atecao)
                .setTitle("Atenção")
                .setMessage("Tem certeza que quer excluir este pacote?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todosOsPacotes.remove(objCorreioDeletado);
                        pacotesFiltrados.remove(objCorreioDeletado);
                        objCorreioDAO.excluirPacoteBD(objCorreioDeletado);
                        lstPacotes.invalidateViews();
                        adaptador.clear();
                        adaptador.addAll(pacotesFiltrados);
                        adaptador.notifyDataSetChanged();
                    }
        }).create();
        confirmacao.show();
    }

    public void alterarPacote(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Correio objPacoteAlterado = pacotesFiltrados.get(menuInfo.position);
        Intent i = new Intent(this,CadastroActivity.class);
        i.putExtra("pacote", objPacoteAlterado);
        startActivity(i);
    }
}