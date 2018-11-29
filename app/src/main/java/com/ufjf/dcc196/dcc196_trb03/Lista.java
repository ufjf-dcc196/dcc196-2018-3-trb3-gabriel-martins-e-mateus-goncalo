package com.ufjf.dcc196.dcc196_trb03;

import java.util.ArrayList;
import java.util.Calendar;

public class Lista {

    private Integer registro;
    private String nome;
    private Calendar data;
    private Double valor;
    private String nomeMercado;
    private ArrayList<ItemDeLista> itens;

    public Lista(Integer registro, String nome, Calendar data, Double valor, String nomeMercado, ArrayList<ItemDeLista> itens) {
        this.registro = registro;
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.nomeMercado = nomeMercado;
        this.itens = itens;
    }

    public Integer getRegistro() {
        return registro;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNomeMercado() {
        return nomeMercado;
    }

    public void setNomeMercado(String nomeMercado) {
        this.nomeMercado = nomeMercado;
    }

    public ArrayList<ItemDeLista> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemDeLista> itens) {
        this.itens = itens;
    }
}
