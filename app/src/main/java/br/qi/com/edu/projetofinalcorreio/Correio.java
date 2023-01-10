package br.qi.com.edu.projetofinalcorreio;

import java.io.Serializable;

public class Correio implements Serializable {
    private String nome;
    private double codigo;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
        this.codigo = codigo;
    }

    public String toString(){
        return "" +
                "ID: "+this.id +"\n" +
                "Nome: "+this.nome +"\n" +
                "Código: " +this.codigo;
    }

}
