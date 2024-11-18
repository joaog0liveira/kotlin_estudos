package ifgoiano.urt.loginauth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import ifgoiano.urt.loginauth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val displayName = currentUser.displayName

            val userName = if (displayName.isNullOrEmpty()) {
                currentUser.email ?: "Usuário sem nome"
            } else {
                displayName + "\nSeja bem-vindo!"
            }
            binding.tvUsuario.text = userName
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.deslogar.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}