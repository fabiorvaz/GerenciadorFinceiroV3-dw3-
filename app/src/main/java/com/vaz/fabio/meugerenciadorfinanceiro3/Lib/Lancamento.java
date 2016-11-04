package com.vaz.fabio.meugerenciadorfinanceiro3.Lib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by usuario on 25/10/2016.
 */

public class Lancamento {
    private int codigo;
    private String descricao;
    private Date dataCriacao;
    private Date dataLancamento;
    private String categoria;
    private boolean pago;
    private boolean tipo;
    private float valor;
    private ArrayList<Parcela> parcelas = new ArrayList<>();

    public Lancamento() {
    }

    public Lancamento(int id, String d,Date dc, Date dl, String c, boolean p, boolean t, float v) {
        codigo = id;
        descricao = d;
        dataCriacao = dc;
        dataLancamento = dl;
        categoria = c;
        pago = p;
        tipo = t;
        valor = v;
    }

    public Lancamento(int id, String d,Date dc, Date dl, String c, boolean p, boolean t, float v, int nParcelas) {
        codigo = id;
        descricao = d;
        dataCriacao = dc;
        categoria = c;
        tipo = t;
        valor = v;

        float valorp = valor/nParcelas;
        Calendar cale = Calendar.getInstance();


        for(int i=0; i<nParcelas;i++)
        {
            //Date dataLancamento, boolean pago, float valor
            cale.setTime(getDataLancamento());
            cale.add(Calendar.MONTH,i);
            parcelas.add(new Parcela(i,codigo, cale.getTime(), p, valorp));
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataLancamento=" + dataLancamento +
                ", categoria='" + categoria + '\'' +
                ", pago=" + pago +
                ", tipo=" + tipo +
                ", valor=" + valor +
                '}';
    }
}
