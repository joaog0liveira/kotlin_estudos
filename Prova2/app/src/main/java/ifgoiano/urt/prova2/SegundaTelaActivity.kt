package ifgoiano.urt.prova2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.prova2.databinding.ActivitySegundaTelaBinding

class SegundaTelaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySegundaTelaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ano = intent.getIntExtra("ANO", 0)
        val semestre = intent.getStringExtra("SEMESTRE")
        val cpf = intent.getStringExtra("CPF")
        val valorDisponivel = intent.getDoubleExtra("VALOR", 0.0)

        val cpfTextView: TextView = binding.tvCpf
        val valorTextView: TextView = binding.tvValor
        cpfTextView.text = "CPF: $cpf"
        valorTextView.text = "Valor disponível: R$$valorDisponivel"

        val bancoSpinner: Spinner = binding.spinnerBanco
        val tipoContaSpinner: Spinner = binding.spinnerTipoConta
        val agenciaEditText: EditText = binding.etAgencia
        val digitoAgenciaEditText: EditText = binding.etDigitoAgencia
        val contaEditText: EditText = binding.etConta
        val digitoContaEditText: EditText = binding.etDigitoConta
        val solicitarSaqueButton: Button = binding.btnSolicitarSaque
        val resumoTextView: TextView = binding.tvResumo

        val valorSaque = calcularValorSaque(ano, valorDisponivel)
        valorTextView.text = "Valor calculado para saque: R$$valorSaque"

        solicitarSaqueButton.setOnClickListener {
            val banco = bancoSpinner.selectedItem.toString()
            val tipoConta = tipoContaSpinner.selectedItem.toString()
            val agencia = agenciaEditText.text.toString()
            val digitoAgencia = digitoAgenciaEditText.text.toString()
            val conta = contaEditText.text.toString()
            val digitoConta = digitoContaEditText.text.toString()

            if (banco.isEmpty() || tipoConta.isEmpty() || agencia.isEmpty() || digitoAgencia.isEmpty() || conta.isEmpty() || digitoConta.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pedido em processamento...", Toast.LENGTH_SHORT).show()

                resumoTextView.text = """
                    Pedido de saque:
                    CPF: $cpf
                    Banco: $banco
                    Tipo de Conta: $tipoConta
                    Agência: $agencia-$digitoAgencia
                    Conta: $conta-$digitoConta
                    Valor Solicitado: R$$valorSaque
                """.trimIndent()
            }
        }
    }

    private fun calcularValorSaque(ano: Int, valor: Double): Double {
        return when (ano) {
            2020 -> valor * 1.0
            2021 -> valor * 0.9
            2022 -> valor * 0.8
            2023 -> valor * 0.7
            2024 -> valor * 0.6
            else -> 0.0
        }
    }
}