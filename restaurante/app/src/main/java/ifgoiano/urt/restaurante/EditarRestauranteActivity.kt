package ifgoiano.urt.restaurante

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class EditarRestauranteActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var btnSalvar: Button
    private lateinit var ratingBar: RatingBar
    private var restauranteId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_restaurante)

        etNome = findViewById(R.id.etNome)
        ratingBar = findViewById(R.id.ratingBar)
        btnSalvar = findViewById(R.id.btnSalvar)

        restauranteId = intent.getStringExtra("id")
        val nome = intent.getStringExtra("nome")
        val classificacao = intent.getFloatExtra("classificacao", 0.0F)

        etNome.setText(nome)
        ratingBar.rating = classificacao

        val rootView = findViewById<LinearLayout>(R.id.linearLayout)

        btnSalvar.setOnClickListener{
            val novoNome = etNome.text.toString()
            val novaClassificacao = ratingBar.rating

            if (restauranteId == null) {
                val restauranteRef = FirebaseFirestore.getInstance().
                        collection("restaurantes").document()

                val restaurante = Restaurante(
                    id = restauranteRef.id,
                    nome = novoNome,
                    classificacao = novaClassificacao
                )

                restauranteRef
                    .set(restaurante)
                    .addOnSuccessListener {
                        Snackbar.make(rootView,
                            "Restaurante cadastro!", Snackbar.LENGTH_SHORT).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 2000)
                    }
                    .addOnFailureListener { e ->
                        Log.w("EditarRestaurante", "Erro ao cadastrar documento", e)
                    }
            } else {
                val restaurante = Restaurante(
                    id = restauranteId!!,
                    nome = novoNome,
                    classificacao = novaClassificacao
                )
                FirebaseFirestore.getInstance().collection("restaurantes")
                    .document(restauranteId!!)
                    .set(restaurante)
                    .addOnSuccessListener {
                        Snackbar.make(findViewById(android.R.id.content),
                            "Restaurante atualizado!", Snackbar.LENGTH_SHORT).show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 2000)
                    }
                    .addOnFailureListener { e ->
                        Log.w("EditarRestaurante", "Erro ao atualizar documento", e)
                    }
            }
        }
    }
}