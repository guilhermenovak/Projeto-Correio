package br.qi.com.edu.projetofinalcorreio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CorreioDAO {
    private SQLiteDatabase bd_pacote;
    private ConexaoBD conexaoBD;

    public CorreioDAO(Context context){
        this.conexaoBD = new ConexaoBD(context);
        this.bd_pacote = conexaoBD.getWritableDatabase();
    }

    public ContentValues gerarValores(Correio objCorreio){
        ContentValues values = new ContentValues();

        values.put("nome", objCorreio.getNome());
        values.put("codigo", objCorreio.getCodigo());

        return values;
    }

    public void cadastrarPacoteBD(Correio objCorreio) {
        ContentValues values = new ContentValues();
        values.put("nome",objCorreio.getNome());
        values.put("codigo", objCorreio.getCodigo());

        this.bd_pacote.insert("tb_pacote ", null,values);
    }

    public void alterarPacotesBD(Correio objCorreio){
        this.bd_pacote.update("tb_pacote",
                this.gerarValores(objCorreio),"id = ?",
                new String[]{String.valueOf(objCorreio.getId())});
    }

    public List<Correio> consultarPacotesBD(){
        List<Correio> encontrados = new ArrayList<>();
        Cursor cursor= bd_pacote.query("tb_pacote", new String[]{"id", "nome", "codigo"},
        null,null,null,null,null);
        while (cursor.moveToNext()) {
            Correio objCorreio = new Correio();
            objCorreio.setId(cursor.getInt(0));
            objCorreio.setNome(cursor.getString(1));
            objCorreio.setCodigo(cursor.getDouble(2));
            encontrados.add(objCorreio);
        }
        return encontrados;
    }

    public void excluirPacoteBD(Correio objCorreio){
        this.bd_pacote.delete("tb_pacote","id = ?",new String[]{String.valueOf(objCorreio.getId())});
    }

}
