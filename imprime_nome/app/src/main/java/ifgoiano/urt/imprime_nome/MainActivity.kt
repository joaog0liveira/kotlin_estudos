package ifgoiano.urt.imprime_nome

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.imprime_nome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOla.setOnClickListener {
            val nome = binding.editNome.text.toString()
            //binding.textResultado.text = "Olá " + nome
            binding.textResultado.text = "Olá ${nome}"
            //binding.textResultado.setText("Olá " + nome)
        }
    }
}