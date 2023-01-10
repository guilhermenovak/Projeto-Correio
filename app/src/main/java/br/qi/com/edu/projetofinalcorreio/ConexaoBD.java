package br.qi.com.edu.projetofinalcorreio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_pacotes";
    private static final int version = 1;


    public ConexaoBD(@Nullable Context context) {
        super(context, name, null, version);}

    public void onCreate(SQLiteDatabase bd_pacotes){
        bd_pacotes.execSQL("create table tb_pacote(id Integer not null primary key autoincrement," +
                "nome varchar(100), codigo double(20))");
    }

        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

        }
}