package com.cursoandroid.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarPorccentagem;
    private TextView textViewPorcentagem, textViewGorjeta, textViewTotal;
    private TextInputEditText inputTextValorConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarPorccentagem     = findViewById(R.id.seekBarPorccentagem);
        textViewPorcentagem     = findViewById(R.id.textViewPorcentagem);
        textViewGorjeta         = findViewById(R.id.textViewGorjeta);
        textViewTotal           = findViewById(R.id.textViewTotal);
        inputTextValorConta     = findViewById(R.id.inputTextValorConta);

        seekBarPorccentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresso, boolean b) {
                textViewPorcentagem.setText(progresso + "%");

                //Obtem valor do campo de texto referente a conta
                String textoCampoValorConta  = inputTextValorConta.getText().toString();

               switch (validar_campos(textoCampoValorConta)) {
                   case "preenchido":
                       //Conversão de String para Double
                       double valorConta = Double.parseDouble(textoCampoValorConta);

                       //Calcula gorjeta
                       double resultado = calcularGorjeta(valorConta, progresso);

                       //Calcula total
                       double total = calcularTotal(valorConta, resultado);

                       //Exibe na view e limita casas decimais
                       textViewGorjeta.setText("R$ " + String.format("%.2f", resultado));
                       textViewTotal.setText("R$ " + String.format("%.2f", total));
                   break;

                   case "conta":
                        Toast.makeText(getApplicationContext(), "Digite o valor da conta", Toast.LENGTH_SHORT).show();
                   break;
               }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    public double calcularGorjeta(double valorConta, double valorPorcentagem){
        double resultado = (valorPorcentagem /100) * valorConta;
        return resultado;
    }

    public String validar_campos(String textoCampoValorConta){
        boolean camposValidados = true;
        String campo = "preenchido";

            if (textoCampoValorConta == null || textoCampoValorConta.equals("")) {
                camposValidados = false;
                campo = "conta";
            }

        return campo;
    }

    public double calcularTotal(double valor, double valorGorjeta){
        double total = valor + valorGorjeta;
        return total;
    }

}
