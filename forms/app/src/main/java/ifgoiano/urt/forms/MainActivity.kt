package ifgoiano.urt.forms

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ifgoiano.urt.forms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // carregar os Estados
        val estadosDisponiveis = resources.getStringArray(R.array.estados)

        // criando adapter para o spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            estadosDisponiveis
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEstados.adapter = adapter

        binding.spinnerEstados.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                    val estadoSelecionado = parent.getItemAtPosition(position).toString()

                    val prefeitosEstados = when (estadoSelecionado) {
                        "Goiás" -> R.array.candidatos_goias
                        "Rio de Janeiro" -> R.array.candidatos_rio
                        "São Paulo" -> R.array.candidatos_sp
                        else -> null
                    }

                    val prefeitosAdapter = prefeitosEstados?.let {
                        ArrayAdapter.createFromResource(
                            this@MainActivity, it, android.R.layout.simple_spinner_item
                        )
                    }

                    if (prefeitosAdapter != null) {
                        prefeitosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                    binding.spinnerPrefeitos.adapter = prefeitosAdapter
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        binding.btnConcluir.setOnClickListener {
            coletarDados()
        }
    }

    private fun coletarDados() {
        val nome = binding.nomeCompleto.text.toString()
        val tituloEleitor = binding.tituloEleitor.text.toString()
        val zona = binding.zona.text.toString()
        val secao = binding.secao.text.toString()
        val cidade = binding.cidade.text.toString()
        val estado = binding.spinnerEstados.selectedItem.toString()
        val prefeito = binding.spinnerPrefeitos.selectedItem.toString()

        val partidosPreferidos = mutableListOf<String>()
        if (binding.partido1.isChecked) partidosPreferidos.add(binding.partido1.text.toString())
        if (binding.partido2.isChecked) partidosPreferidos.add(binding.partido2.text.toString())
        if (binding.partido3.isChecked) partidosPreferidos.add(binding.partido3.text.toString())


        val mensagem = "Nome: $nome\nTítulo de Eleitor: $tituloEleitor" +
                "\nZona: $zona\nSeção: $secao\nCidade: $cidade" +
                "\nEstado: $estado\nPrefeito: $prefeito" +
                "\nPartidos Preferidos: ${partidosPreferidos.joinToString(", ")}"

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}