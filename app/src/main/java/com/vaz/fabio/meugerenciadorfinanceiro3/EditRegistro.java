package com.vaz.fabio.meugerenciadorfinanceiro3;

import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Controle;
import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Lancamento;

import java.util.Calendar;

public class EditRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registro);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String idSelecionado = extras.getString("id");
            Lancamento lancamentoSelecionado = Controle.instancia.getLancamentoById(Integer.parseInt(idSelecionado));
            ( (TextView) findViewById(R.id.editTxtDataCriacao)).setText(lancamentoSelecionado.getDataCriacao().toString());

            //TODO: arrumar a função de pegar uma variavel date e lançar dentro do datepicker

            //( (DatePicker) findViewById(R.id.editDtpDataLancamento)).updateDate(1,1,1111);

            ( (EditText) findViewById(R.id.editTxtDescricao)).setText(lancamentoSelecionado.getDescricao());
            //( (Spinner) findViewById(R.id.editDdCategoria)).(lancamentoSelecionado.getDataCriacao().toString());
            ( (EditText) findViewById(R.id.editTxtValor)).setText( String.valueOf(lancamentoSelecionado.getValor()));
        }
    }
}
