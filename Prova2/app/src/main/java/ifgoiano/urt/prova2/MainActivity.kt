package ifgoiano.urt.prova2

import SemestreAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ifgoiano.urt.prova2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cpfEditText: EditText = binding.editTextCPF
        val valorDisponivelEditText: EditText = binding.editTextValorDisponivel
        val creditosNaoLiberadosEditText: EditText = binding.editTextCreditosNaoLiberados

        val semestres = SemestreData.getSemestre()

        val recyclerView = binding.recyclerViewSemestres
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SemestreAdapter(semestres) { semestre ->
            onClickSemestre(semestre)
        }
    }

    private fun onClickSemestre(semestre: Semestre) {
        val cpf = binding.editTextCPF.text.toString()
        val valorDisponivel = binding.editTextValorDisponivel.text.toString().toDouble()

        val intent = Intent(this, SegundaTelaActivity::class.java).apply {
            putExtra("ANO", semestre.ano)
            putExtra("SEMESTRE", semestre.semestre)
            putExtra("CPF", cpf)
            putExtra("VALOR", valorDisponivel)
        }
        startActivity(intent)
    }
}