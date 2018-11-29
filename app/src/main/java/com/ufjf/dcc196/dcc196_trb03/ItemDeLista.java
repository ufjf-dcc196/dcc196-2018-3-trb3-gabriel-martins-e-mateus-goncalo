package com.ufjf.dcc196.dcc196_trb03;

public class ItemDeLista {

    private Integer codigoItem;
    private String nomeItem;
    private Integer quantidadeItem;
    private Double valor;

    public ItemDeLista(Integer codigoItem, String nomeItem, Integer quantidadeItem, Double valor) {
        this.codigoItem = codigoItem;
        this.nomeItem = nomeItem;
        this.quantidadeItem = quantidadeItem;
        this.valor = valor;
    }

    public ItemDeLista() {
    }

    public Integer getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(Integer codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Integer getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(Integer quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
