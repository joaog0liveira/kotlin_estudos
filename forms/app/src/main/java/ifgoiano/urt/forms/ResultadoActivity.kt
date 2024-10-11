package ifgoiano.urt.forms

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.forms.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        val tituloEleitor = intent.getStringExtra("tituloEleitor")
        val zona = intent.getStringExtra("zona")
        val secao = intent.getStringExtra("secao")
        val cidade = intent.getStringExtra("cidade")
        val estado = intent.getStringExtra("estado")
        val prefeito = intent.getStringExtra("prefeito")
        val partidosPreferidos = intent.getStringArrayListExtra("partidos")

        binding.resultado.text = """
            Nome: $nome
            Título de Eleitor: $tituloEleitor
            Zona: $zona
            Seção: $secao
            Cidade: $cidade
            Estado: $estado
            Prefeito: $prefeito
            Partidos Preferidos: ${partidosPreferidos?.joinToString(", ")}
        """.trimIndent()

        binding.buttonFechar.setOnClickListener {
            finishAffinity()
        }
    }
}