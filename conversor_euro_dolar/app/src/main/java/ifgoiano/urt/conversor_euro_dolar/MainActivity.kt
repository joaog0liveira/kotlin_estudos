package ifgoiano.urt.conversor_euro_dolar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.conversor_euro_dolar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConverter.setOnClickListener{
            val euros = binding.editEuro.text.toString().toDouble()
            val dolares = String.format("%.2f", euros * 0.8)

            binding.textDolares.text = "${dolares} $"
        }
    }
}