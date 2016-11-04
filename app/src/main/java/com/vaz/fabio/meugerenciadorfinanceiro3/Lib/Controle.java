package com.vaz.fabio.meugerenciadorfinanceiro3.Lib;

import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by usuario on 25/10/2016.
 */

public class Controle {
    public static Controle instancia;

    public static void initInstancia(){
        if (instancia == null){
            instancia = new Controle();
        }
    }

    public static Controle getInstancia() {
        return instancia;
    }

    public ArrayList<Lancamento> listaLancamento = new ArrayList<Lancamento>();

    public void cadastraLancamento(String categoria, String descricao, float valor, Date lancamento, boolean tipo, boolean pago)
    {
        listaLancamento.add(new Lancamento(calculaCodigo(), descricao, Calendar.getInstance().getTime(), lancamento, categoria, pago, tipo, valor));
    }

    public void atualizaLancamento(String categoria, String descricao, float valor, Date lancamento, int cod, boolean pago)
    {
        for(int i=0;i<listaLancamento.size();i++)
        {
            Lancamento temp = listaLancamento.get(i);
            if(temp.getCodigo() == cod)
            {
                temp.setCategoria(categoria);
                temp.setDataLancamento(lancamento);
                temp.setDescricao(descricao);
                temp.setValor(valor);
                temp.setPago(pago);
                listaLancamento.set(i, temp);
                return;
            }
        }
    }

    public void deletaRegistro(int cod)
    {
        int i = 0;
        for(i=0;i<listaLancamento.size();i++)
        {
            Lancamento temp = listaLancamento.get(i);
            if(temp.getCodigo() == cod)
                break;
        }
        listaLancamento.remove(i);
    }

    private int calculaCodigo()
    {
        if(listaLancamento.size()>=1)
            return listaLancamento.get(listaLancamento.size()-1).getCodigo()+1;
        else
            return 1;
    }

    public java.util.Date getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public Lancamento getLancamentoById(int id)
    {
        for (Lancamento l:listaLancamento)
        {
            if(l.getCodigo() == id)
                return l;
        }
        return null;
    }

    public ArrayList<Lancamento> getLancamentosByMonthYear (Date referencia) {
        ArrayList<Lancamento> retorno = new ArrayList<>();
        for (Lancamento l : listaLancamento)
        {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(referencia);
            cal2.setTime(l.getDataLancamento());
            boolean sameMonth = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            if(sameMonth)
                retorno.add(l);
        }
        return retorno;

    }
}
