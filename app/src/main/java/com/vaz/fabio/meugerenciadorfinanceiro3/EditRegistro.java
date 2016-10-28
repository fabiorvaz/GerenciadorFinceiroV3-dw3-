package com.vaz.fabio.meugerenciadorfinanceiro3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Controle;
import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Lancamento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditRegistro extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registro);

        Bundle extras = getIntent().getExtras();
        Spinner spinner = (Spinner) findViewById(R.id.editDdCategoria);

        if (extras != null) {

            String idSelecionado = extras.getString("id");
            Calendar c = Calendar.getInstance();

            Lancamento lancamentoSelecionado = Controle.instancia.getLancamentoById(Integer.parseInt(idSelecionado));
            java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

            ( (TextView) findViewById(R.id.editTxtDataCriacao)).setText(dateFormat.format(lancamentoSelecionado.getDataCriacao()));

            c.setTime(lancamentoSelecionado.getDataLancamento());
            ( (DatePicker) findViewById(R.id.editDtpDataLancamento)).updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

            ( (EditText) findViewById(R.id.editTxtDescricao)).setText(lancamentoSelecionado.getDescricao());
            ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.categorias, R.layout.support_simple_spinner_dropdown_item);
            ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(ad);

            ( (Spinner) findViewById(R.id.editDdCategoria)).setSelection(ad.getPosition(lancamentoSelecionado.getCategoria()));
            ( (EditText) findViewById(R.id.editTxtValor)).setText( String.valueOf(lancamentoSelecionado.getValor()));

            if(lancamentoSelecionado.isTipo())
            {
                ( (TextView) findViewById(R.id.editTxtDespesa)).setText(R.string.receita);
            }
            else
            {
                ( (TextView) findViewById(R.id.editTxtDespesa)).setText(R.string.despesa);
            }
        }

        Button btnSalvarLancamento = (Button) findViewById(R.id.btnSalvarLancamento);
        btnSalvarLancamento.setOnClickListener(this);

        Button btnCancelarLancamento = (Button) findViewById(R.id.btnCancelarLancamento);
        btnCancelarLancamento.setOnClickListener(this);

        Button btnDeletarLancamento = (Button) findViewById(R.id.btnDeletarLancamento);
        btnDeletarLancamento.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        View viewClicado = findViewById(view.getId());
        EditText campoTexto;

        switch (viewClicado.getId()) {
            case R.id.btnCancelarLancamento:
                this.finish();
                break;
            case R.id.btnSalvarLancamento:
                salvarLancamento();
                this.finish();
                break;
            case R.id.btnDeletarLancamento:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Excluir registro")
                        .setMessage("Você tem certeza que deseja excluir esse registro?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle extras = getIntent().getExtras();
                                Controle.instancia.deletaRegistro(Integer.parseInt(extras.getString("id")));
                                mataTela();
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
                break;
            default:
                return;
        }
    }

    private void mataTela()
    {
        this.finish();
    }

    private void salvarLancamento()
    {
        String categoria = ((Spinner) findViewById(R.id.editDdCategoria)).getSelectedItem().toString();
        String descricao = ((EditText) findViewById(R.id.editTxtDescricao)).getText().toString();
        float valor =  Float.valueOf(((EditText) findViewById(R.id.editTxtValor)).getText().toString());
        Date lancamento = Controle.instancia.getDateFromDatePicket((DatePicker) findViewById(R.id.editDtpDataLancamento));
        Bundle extras = getIntent().getExtras();

        Controle.instancia.atualizaLancamento(categoria,descricao,valor,lancamento,Integer.parseInt(extras.getString("id")));
    }
}
