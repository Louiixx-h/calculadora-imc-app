package br.com.smdev.indicecorporal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private double peso;
    private double altura;
    private EditText inputPeso;
    private EditText inputAltura;
    private TextView inputTextIMC;
    private TextView results;
    private TextView unidadeMedida;
    private DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextIMC = (TextView) findViewById(R.id.meuImc);
        results = (TextView) findViewById(R.id.results);
        unidadeMedida = (TextView) findViewById(R.id.unidadeMedida);
        inputPeso = (EditText) findViewById(R.id.inputPeso);
        inputAltura = (EditText) findViewById(R.id.inputAltura);
        formatter = new DecimalFormat("#00.00");
    }

    public void calcularIMC(View view) {
        if(verificarValor(inputAltura) == 0 || verificarValor(inputPeso) == 0 ){
            Toast.makeText(this, "Preencha os campos!", Toast.LENGTH_SHORT).show();
        } else {
            peso = Double.parseDouble(inputPeso.getText().toString());
            altura = (Double.parseDouble(inputAltura.getText().toString()))/100;

            if (altura == 0 || altura < 0 || peso == 0 || peso < 0) {
                inputTextIMC.setText("Use Valores Válidos!");
            } else {
                double imc = peso / (altura * altura);

                inputTextIMC.setText(formatter.format(imc));
                unidadeMedida.setText("Kg/m²");
                results.setText(classificacao(imc));
            }
        }
    }

    private double verificarValor(TextView valor) {
        String text = valor.getText().toString();
        if(text.equals("")) {
            return 0;
        } else {
            return Double.parseDouble(valor.getText().toString());
        }
    }

    private String classificacao(double imc) {
        String classificacao = "";

        if(imc <= 18.5){
            classificacao = "Magreza!";
        } else if(imc > 18.5 && imc <= 24.9){
            classificacao = "Normal.";
        } else if(imc > 24.9 && imc <= 29.9){
            classificacao = "Sobrepeso.";
        } else if(imc > 29.9 && imc <= 39.9){
            classificacao = "Obesidade!";
        } else if(imc > 39.9){
            classificacao = "Obesidade Grave!";
        }
        return classificacao;
    }
}