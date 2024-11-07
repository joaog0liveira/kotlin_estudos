package ifgoiano.urt.produtosrecycler2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.produtosrecycler2.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        val descricao = intent.getStringExtra("descricao")
        val preco = intent.getStringExtra("preco")
        val img = intent.getIntExtra("img", 0)

        binding.nomeProduto.text = nome
        binding.descricaoProduto.text = "Descricão: $descricao"
        binding.precoProduto.text = "Preço: $preco"
        binding.imgProduto.setImageResource(img)

        binding.btnVoltar.setOnClickListener {
            finish()
        }

    }
}