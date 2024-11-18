package ifgoiano.urt.loginauth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import ifgoiano.urt.loginauth.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Por favor, preencha todos os campos",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (password.length < 6) {
                Snackbar.make( binding.root,
                    "A senha precisa ter pelo menos 6 caracteres",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                registerUser(name, email, password)
            }
        }
        binding.btnSelectPhoto.setOnClickListener {
            Snackbar.make( binding.root,
                "Funcionalidade indisponível no momento",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
    private fun registerUser(name: String, email: String, password: String) {
// Exibe o ProgressBar enquanto tenta registrar o usuário
        binding.loading.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.loading.visibility = View.GONE // Oculta o ProgressBar
                if (task.isSuccessful) {
// Salva o nome no perfil do usuário
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            Snackbar.make(binding.root,
                                "Cadastro realizado com sucesso", Snackbar.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Snackbar.make(binding.root,
                                "Falha ao atualizar perfil: ${updateTask.exception?.message}", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Snackbar.make(binding.root, "Erro ao registrar: ${task.exception?.message}",
                        Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}