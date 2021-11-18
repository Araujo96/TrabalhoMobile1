package com.example.hello_aula

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ActivityRecebe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recebe)
        val botaoCancelar:Button = findViewById(R.id.botaoCancelar)
        botaoCancelar.setOnClickListener {
            voltarTela()
        }
        val botaoAlterar: Button = findViewById(R.id.AlterarDados);
        botaoAlterar.setOnClickListener {
            val dadoPassado:TextView? = findViewById(R.id.dadoPassado)

            when {
                intent.getStringExtra("Origem").toString() == "Altura" -> {
                    intent.putExtra("Altura", dadoPassado?.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                }
                intent.getStringExtra("Origem").toString() == "Peso" -> {
                    intent.putExtra("Peso", dadoPassado?.text.toString())
                    setResult(Activity.RESULT_FIRST_USER, intent)
                }
                else -> {
                    intent.putExtra("Erro", "Deu ruim")
                }
            }

            finish()
        }
    }
    private fun voltarTela(){
        val voltar = Intent(this,MainActivity::class.java)
        startActivity(voltar)
    }
}
