package com.vaz.fabio.meugerenciadorfinanceiro3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Controle;
import com.vaz.fabio.meugerenciadorfinanceiro3.Lib.Lancamento;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    public void init() {
        Controle.initInstancia();
        Button btnNovo = (Button) findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(this);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        stk.removeAllViews();

        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(R.string.lancamento);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(R.string.categoria);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(R.string.valor);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(R.string.pago);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(R.string.tipo);
        tbrow0.addView(tv4);
        stk.addView(tbrow0);

        for (Lancamento l : Controle.instancia.listaLancamento) {
            TableRow tbrow = new TableRow(this);

            java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());


            Button btn = new Button(this);
            btn.setText(dateFormat.format(l.getDataLancamento()));
            btn.setTag("id:"+l.getCodigo());
            btn.setOnClickListener(this);



            tbrow.addView(btn);

            TextView t2v = new TextView(this);
            t2v.setText(l.getCategoria());
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText(String.valueOf(l.getValor()));
            tbrow.addView(t3v);

            TextView t4v = new TextView(this);
            if(l.isPago())
                t4v.setText(R.string.pago);
            else
                t4v.setText(R.string.naoPago);
            tbrow.addView(t4v);

            TextView t5v = new TextView(this);
            if(l.isTipo())
                t5v.setText(R.string.receita);
            else
                t5v.setText(R.string.despesa);
            tbrow.addView(t5v);
            stk.addView(tbrow);

        }
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        init();
    }

    @Override
    public void onClick(View view) {
        Button btnClicado = (Button) findViewById(view.getId());

        Intent inn1;
        if(view.getTag() != null)
        {
            String tag = view.getTag().toString();
            if (tag != "") {
                inn1 = new Intent(this, EditRegistro.class);
                inn1.putExtra("id", tag.replace("id:", ""));
                startActivity(inn1);
                return;
            }
        }

        switch (btnClicado.getId()) {
            case R.id.btnNovo:
                inn1 = new Intent(this, novo_registro.class);
                startActivity(inn1);
                break;

            default:
                return;
        }
    }
}