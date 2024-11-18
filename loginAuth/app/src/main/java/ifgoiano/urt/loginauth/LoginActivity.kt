package ifgoiano.urt.loginauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import ifgoiano.urt.loginauth.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
// Verifica se o usuário já está logado
        if (auth.currentUser != null) {
// Se estiver logado, vai direto para a MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
// Configura o botão de Sign In
        binding.login.setOnClickListener {
            Snackbar.make(
                binding.root, "Botão Login clicado",
                Snackbar.LENGTH_SHORT
            ).show()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
// Verifica se os campos estão preenchidos
            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Por favor, preencha todos os campos",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            signIn(email, password)
        }
// Configura o botão de Registrar
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        Log.d("LoginActivity", "Tentativa de login")
// Exibe o ProgressBar
        binding.loading.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, password).
        addOnCompleteListener(this) { task ->
// Esconde o ProgressBar após a tentativa
            binding.loading.visibility = View.GONE
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(binding.root, "Falha ao entrar: ${task.exception?.message}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    // Método de registro de novo usuario
    private fun register(email: String, password: String) {
        Log.d("LoginActivity", "Tentativa de registro")
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(
                    binding.root,
                    "Falha ao registrar: ${task.exception?.message}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
