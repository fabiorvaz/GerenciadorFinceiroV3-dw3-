package com.vaz.fabio.meugerenciadorfinanceiro3.Lib;

import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
}
