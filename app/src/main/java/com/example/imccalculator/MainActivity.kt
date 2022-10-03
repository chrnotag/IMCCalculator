package com.example.imccalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.imccalculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater);
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.calcular.setOnClickListener {
            calcularImc()
            closekeyboard()
        }

        binding.pesoinformado.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                calcularImc()
                closekeyboard()
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    private fun closekeyboard() {
        val view = currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun calcularImc() {
        val altura = binding.alturainformada.text.toString().toDouble()
        val peso = binding.pesoinformado.text.toString().toDouble()

        val resultado = peso / (altura * altura)

        val df = DecimalFormat("#.00")

        checkImc(df.format(resultado).toString().toDouble())
    }

    private fun checkImc(imc: Double) {
        when (imc) {
            in 0.0..17.0 -> {
                binding.pesoIMC.text = "Muito abaixo do peso."
                binding.margemIMC.text = "IMC entre 0 e 17.0"
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.msgIMC.text = resources.getText(R.string.abaixo_peso)

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
                binding.resultIMC.visibility = View.VISIBLE
            }
            in 17.1..18.49 -> {
                binding.pesoIMC.text = "Abaixo do peso."
                binding.margemIMC.text = "IMC entre 17.1 e 18.49"
                binding.msgIMC.text = resources.getText(R.string.abaixo_peso)
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.resultIMC.visibility = View.VISIBLE

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
            }
            in 18.5..24.99 -> {
                binding.pesoIMC.text = "Peso Normal"
                binding.margemIMC.text = "IMC entre 18.5 e 24.99"
                binding.msgIMC.text = resources.getText(R.string.normal_peso)
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.resultIMC.visibility = View.VISIBLE

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
            }
            in 25.0..29.99 -> {
                binding.pesoIMC.text = "Sobrepeso"
                binding.margemIMC.text = "IMC entre 25.0 e 29.99"
                binding.msgIMC.text = resources.getText(R.string.sobrepeso)
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.resultIMC.visibility = View.VISIBLE

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
            }
            in 30.0..34.99 -> {
                binding.pesoIMC.text = "Obesidade I"
                binding.margemIMC.text = "IMC entre 30.0 e 34.99"
                binding.msgIMC.text = resources.getText(R.string.obesidade1)
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.resultIMC.visibility = View.VISIBLE

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
            }
            in 35.0..39.99 -> {
                binding.pesoIMC.text = "Obesidade II"
                binding.margemIMC.text = "IMC entre 35.0 e 39.99"
                binding.msgIMC.text = resources.getText(R.string.obesidade2)
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.resultIMC.visibility = View.VISIBLE

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
            }
            else -> {
                binding.pesoIMC.text = "Obesidade III (Mórbida)"
                binding.margemIMC.text = "IMC acima de 40.0"
                binding.msgIMC.text = resources.getText(R.string.obesidade3)
                binding.resultIMC.text = "Seu IMC é: ${imc.toString()}"
                binding.resultIMC.visibility = View.VISIBLE

                binding.margemIMC.visibility = View.VISIBLE
                binding.msgIMC.visibility = View.VISIBLE
                binding.pesoIMC.visibility = View.VISIBLE
            }
        }
    }
}