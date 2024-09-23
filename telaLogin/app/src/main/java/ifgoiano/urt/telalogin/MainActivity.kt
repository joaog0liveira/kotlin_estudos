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

            if (email == "teste" && senha == "120103") {

                val bundle = Bundle()
                bundle.putString("username", email)

                val intent = Intent(this, LogadoActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            } else {
                alert("Login e senha incorretos")
            }
        }
    }

    private fun alert(s: String) {
        Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
        binding.emailText.text.clear()
        binding.senhaText.text.clear()
    }
}
