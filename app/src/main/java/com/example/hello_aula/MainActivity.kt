package com.example.hello_aula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var altura = 0.0000F
    var peso = 0.0000F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultadoFinal: TextView = findViewById(R.id.resultadoFinal)

        val activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            val param = result.data?.extras!!
            when(result.resultCode){
                RESULT_OK -> {
                    altura = param.getString("Altura")?.toFloat() ?: 0.0000F;
                    val dadoRecebido2:TextView = findViewById(R.id.dadoRecebido2);
                    dadoRecebido2.text = altura.toString();
                }
                RESULT_FIRST_USER -> {
                    peso = param.getString("Peso")?.toFloat() ?: 0.0000F;
                    val dadoRecebidoText:TextView = findViewById(R.id.dadoRecebidoText);
                    dadoRecebidoText.text = peso.toString();
                }
                RESULT_CANCELED -> {
                    Toast.makeText(this,"cancelou",Toast.LENGTH_SHORT).show()
                }
            }

        }

        val botaoAltura: Button = findViewById(R.id.alterarAltura);
        botaoAltura.setOnClickListener {
            val intent= Intent(this,ActivityRecebe::class.java)
            intent.putExtra("Origem", "Altura")
            activityForResult.launch(intent)
        }

        val botaoPeso: Button = findViewById(R.id.alterarPeso);
        botaoPeso.setOnClickListener {
            val intent= Intent(this,ActivityRecebe::class.java)
            intent.putExtra("Origem", "Peso")
            activityForResult.launch(intent)
        }

        val butaoCalcular:Button = findViewById(R.id.calcularResultado)
        butaoCalcular.setOnClickListener {
            calc(peso, altura, resultadoFinal)
        }
    }

    private fun calc(peso: Float, altura: Float, resultadoFinal: TextView ) {
        val alt = altura.times(altura)
        val res = peso.div(alt)
        resultadoFinal.text = (res*10000).toString()
        if(res*10000 < 16){
            resultadoFinal.text = "Magreza Grave seu IMC é de:${res*10000}"
        }else if(res*10000 >= 16 && res*10000 <17){
            resultadoFinal.text = "Magreza Moderada seu IMC é de:${res*10000}"
        }else if(res*10000 >= 17 && res*10000 <18.5){
            resultadoFinal.text = "Magreza Leve seu IMC é de:${res*10000}"
        }else if(res*10000 >= 18.5 && res*10000 <25){
            resultadoFinal.text = "Saudável seu IMC é de:${res*10000}"
        }else if(res*10000 >= 25 && res*10000 <30){
            resultadoFinal.text = "Sobrepeso seu IMC é de:${res*10000}"
        }else if(res*10000 >= 30 && res*10000 <35){
            resultadoFinal.text = "Obesidade Grau I seu IMC é de:${res*10000}"
        }else if(res*10000 >= 35 && res*10000 <40){
            resultadoFinal.text = "Obesidade Grau II(Severa) seu IMC é de:${res*10000}"
        }else if(res*10000 > 40){
            resultadoFinal.text = "Obesidade Grau III (Mórbida) seu IMC é de:${res*10000}"
        }
        else{
            Toast.makeText(this,"Erro no Calculo",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("cliclo de vida","onStart)")

    }

    override fun onResume() {
        super.onResume()
        Log.i("cliclo de vida","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("cliclo de vida","onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("cliclo de vida","onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("cliclo de vida","onDestroyer()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("cliclo de vida","onRestart()");
    }
}
