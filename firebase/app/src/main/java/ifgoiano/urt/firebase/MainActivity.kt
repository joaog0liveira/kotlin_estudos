package ifgoiano.urt.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import ifgoiano.urt.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore
    private var cidade: String = ""
    private var pais: String = ""
    private var sigla: String = ""
    private lateinit var paises: Paises

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()



        binding.btnCriar.setOnClickListener { criar() }
        binding.btnLer.setOnClickListener { ler() }
        binding.btnAtualizar.setOnClickListener { atualizar() }
        binding.btnExcluir.setOnClickListener { deletar() }
    }

    private fun criar() {
        val capitais = db.collection("paises")
        cidade = binding.editTextNome.text.toString()
        pais = binding.editTextPais.text.toString()
        sigla = binding.editTextSigla.text.toString()

        when {
            TextUtils.isEmpty(cidade) -> {
                binding.editTextNome.error = "Digite o nome da cidade"
                return
            }
            TextUtils.isEmpty(pais) -> {
                binding.editTextPais.error = "Digite o nome do país"
                return
            }
            TextUtils.isEmpty(sigla) -> {
                binding.editTextSigla.error = "Digite a sigla do país"
                return
            }
        }
        paises = Paises(cidade, pais, sigla)
        capitais.document(sigla).set(paises)
            .addOnSuccessListener {
                binding.editTextNome.text.clear()
                binding.editTextPais.text.clear()
                binding.editTextSigla.text.clear()
                binding.textViewAviso.text = "Inserido com sucesso!"
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Erro ao inserir documento", e)
                binding.textViewAviso.text = "Erro ao inserir, tente novamente."
            }
    }

    private fun ler() {
        cidade = binding.editTextNome.text.toString()
        db.collection("paises")
            .whereEqualTo("capital", cidade)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val c = document.toObject(Paises::class.java)

                        val intent = Intent(this, DetalhesActivity::class.java).apply {
                            putExtra("capital", c.capital)
                            putExtra("pais", c.pais)
                            putExtra("sigla", c.sigla)
                        }

                    }
                } else {
                    Log.d("TAG", "Erro ao buscar documentos",
                        task.exception)
                }
            }
    }

    private fun atualizar() {
        sigla = binding.editTextSigla.text.toString()
        db.collection("paises").document(sigla)
            .update(
                "capital", binding.editTextNome.text.toString(),
                "pais", binding.editTextPais.text.toString()
            )
            .addOnSuccessListener {
                binding.textViewAviso.text = "Atualizado!"
                ler()
            }
            .addOnFailureListener {
                Log.d("TAG", "Falha ao atualizar", it)
            }
    }

    private fun deletar() {
        sigla = binding.editTextSigla.text.toString()
        db.collection("paises").document(sigla)
            .delete()
            .addOnSuccessListener {
                binding.textViewAviso.text = "Deletado!"
            }
            .addOnFailureListener {
                Log.w("TAG", "Erro ao deletar!", it)
            }
    }
}