package ifgoiano.urt.firebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ifgoiano.urt.firebase.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val capital = intent.getStringExtra("capital")
        val pais = intent.getStringExtra("pais")
        val sigla = intent.getStringExtra("sigla")

        binding.textViewCapital.text = capital
        binding.textViewPais.text = pais
        binding.textViewSigla.text = sigla
    }
}