package com.vaz.fabio.meugerenciadorfinanceiro3;

import android.content.Intent;
import android.icu.util.DateInterval;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Controle;

import java.util.Calendar;
import java.util.Date;

public class novo_registro extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_registro);

        init();
    }

    public void init() {
        Spinner spinner = (Spinner) findViewById(R.id.ddCategoria);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.categorias, R.layout.support_simple_spinner_dropdown_item);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        Button btnSalvarLancamento = (Button) findViewById(R.id.btnSalvarLancamento);
        btnSalvarLancamento.setOnClickListener(this);

        Button btnCancelarLancamento = (Button) findViewById(R.id.btnCancelarLancamento);
        btnCancelarLancamento.setOnClickListener(this);

        final SeekBar sk=(SeekBar) findViewById(R.id.seekBar4);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub

                ((TextView) findViewById(R.id.lbl_parcelas_atuais)).setText(String.valueOf(progress + 1));

            }
        });

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
            default:
                return;
        }
    }

    private void salvarLancamento()
    {
        String categoria = ((Spinner) findViewById(R.id.ddCategoria)).getSelectedItem().toString();
        String descricao = ((EditText) findViewById(R.id.txtDescricao)).getText().toString();
        float valor =  Float.valueOf(((EditText) findViewById(R.id.txtValor)).getText().toString());
        Date lancamento = Controle.instancia.getDateFromDatePicket((DatePicker) findViewById(R.id.txtDataLancamento));
        boolean tipo = ((ToggleButton) findViewById(R.id.tgBtnReceita)).isChecked();
        boolean pago = ((ToggleButton) findViewById(R.id.tgBtnPago)).isChecked();
        int parcelas = Integer.parseInt(((TextView) findViewById(R.id.lbl_parcelas_atuais)).getText().toString());
        float valorp =valor/parcelas;
        Calendar cale = Calendar.getInstance();


        for(int i=0; i<parcelas;i++)
        {
            cale.setTime(lancamento);
            cale.add(Calendar.MONTH,i);
            Controle.instancia.cadastraLancamento(categoria, descricao, valorp, cale.getTime(), tipo, pago);
        }
    }
}
