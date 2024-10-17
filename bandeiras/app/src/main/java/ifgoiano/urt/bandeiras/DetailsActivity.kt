package ifgoiano.urt.bandeiras

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ifgoiano.urt.bandeiras.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        val capital = intent.getStringExtra("capital")
        val populacao = intent.getStringExtra("populacao")
        val regiao = intent.getStringExtra("regiao")
        val img = intent.getIntExtra("img", 0)

        binding.nomeEstado.text = nome
        binding.capitalEstado.text = "Capital: $capital"
        binding.populacaoEstado.text = "População: $populacao"
        binding.regiaoEstado.text = "Região: $regiao"
        binding.imgBandeira.setImageResource(img)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}