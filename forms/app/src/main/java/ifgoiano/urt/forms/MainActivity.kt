package ifgoiano.urt.forms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.forms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val estadosDisponiveis = resources.getStringArray(R.array.estados)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            estadosDisponiveis,

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

                    val vereadorEstados = when (estadoSelecionado) {
                        "Goiás" -> R.array.vereador_go
                        "Rio de Janeiro" -> R.array.vereador_rio
                        "São Paulo" -> R.array.vereador_sp
                        else -> null
                    }

                    val prefeitosAdapter = prefeitosEstados?.let {
                        ArrayAdapter.createFromResource(
                            this@MainActivity, it, android.R.layout.simple_spinner_item
                        )
                    }

                    val vereadoresAdapter = vereadorEstados?.let {
                        ArrayAdapter.createFromResource(
                            this@MainActivity, it, android.R.layout.simple_spinner_item
                        )
                    }

                    if (prefeitosAdapter != null && vereadoresAdapter != null) {
                        prefeitosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        vereadoresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                    binding.spinnerPrefeitos.adapter = prefeitosAdapter
                    binding.spinnerVereadores.adapter = vereadoresAdapter
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

        val partidos = mutableListOf<String>()
        if (binding.partido1.isChecked) partidos.add(binding.partido1.text.toString())
        if (binding.partido2.isChecked) partidos.add(binding.partido2.text.toString())
        if (binding.partido3.isChecked) partidos.add(binding.partido3.text.toString())

        val intent = Intent(this, ResultadoActivity::class.java)

        intent.putExtra("nome", nome)
        intent.putExtra("tituloEleitor", tituloEleitor)
        intent.putExtra("zona", zona)
        intent.putExtra("secao", secao)
        intent.putExtra("cidade", cidade)
        intent.putExtra("estado", estado)
        intent.putExtra("prefeito", prefeito)
        intent.putStringArrayListExtra("partidos", ArrayList(partidos))

        startActivity(intent)
    }
}