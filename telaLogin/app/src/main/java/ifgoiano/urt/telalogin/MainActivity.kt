package ifgoiano.urt.telalogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ifgoiano.urt.telalogin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val emailTeste = "teste"
    private val senhaTeste = "120103"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogar.setOnClickListener {

            val email = binding.emailText.text.toString()
            val senha = binding.senhaText.text.toString()

            if (emailTeste == email && senhaTeste == senha) {
                val intent = Intent(this, LogadoActivity::class.java)
                startActivity(intent)
            } else {
                alert("Login e senha incorretos")
            }
        }
    }

    private fun alert(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}
