package com.example.calculaimcentrega

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.calculaimcentrega.databinding.ActivityCalculaImcactivityBinding

class CalculaIMCActivity : AppCompatActivity() {
    private var resultado: Double = 0.0
    private var tipo : String = "1"
    private var ok : Int = 0
    private lateinit var binding: ActivityCalculaImcactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculaImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarListeners()
    }

    private fun configurarListeners(){
        configurarButtomListener()
        configurarRG()
    }


    private fun configurarButtomListener() {
        binding.btnCalcula.setOnClickListener {
            iniciarCalcActvity()
        }
    }
    private fun iniciarCalcActvity() {
        calculaIMC()
        if (ok == 1) {
            configurarRG()
            val imc = resultado.toString()
            val nome = binding.EdtNome.text.toString()
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra(TAG, imc)
                putExtra(TAG1, tipo)
                putExtra(TAG2, nome)
            }
            startActivity(intent)
            binding.EdtAltura.text.clear()
            binding.EdtPeso.text.clear()
            binding.EdtNome.text.clear()
            binding.txtMensagem.text = ""
        }
    }

    private fun calculaIMC(){
        val peso   = binding.EdtPeso.text.toString().toDouble()
        val altura = binding.EdtAltura.text.toString().toDouble()
        ok = 0

        if(binding.EdtPeso.text.isBlank()){
            binding.EdtPeso.error = "Os campos Peso é onbrigatório."
        }else{
            if(binding.EdtAltura.text.isBlank()) {
                binding.EdtAltura.error = "Os campos 'Altura' é onbrigatório."
            }else{
                resultado = peso / ((altura * altura)/10000)
                ok = 1
            }
        }
    }

    private fun configurarRG(){
        binding.RDGIdade.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rdbAdulto -> {
                    Toast.makeText(this, "Adulto", Toast.LENGTH_LONG).show()
                    mudarCorBotao(1)
                    tipo = "1"
                }
                R.id.rdbIdoso -> {
                    Toast.makeText(this, "Idoso", Toast.LENGTH_LONG).show()
                    mudarCorBotao(2)
                    tipo = "2"
                }
            }
        }
    }

    private fun mudarCorBotao(i: Int) {
        val color = when (i) {
            2 -> {
                Log.d("PUCMINAS", "Cor Verde Claro")
                getColor(R.color.teal_200)
            }
            else -> {
                Log.d("PUCMINAS", "Cor Verde")
                getColor(R.color.teal_700)
            }
        }
        binding.btnCalcula.setBackgroundColor(color)
    }

    companion object{
        const val TAG = "CALC_IMC_INTENT"
        const val TAG1 = "CALC_IMC_INTENT1"
        const val TAG2 = "CALC_IMC_INTENT2"
    }
}