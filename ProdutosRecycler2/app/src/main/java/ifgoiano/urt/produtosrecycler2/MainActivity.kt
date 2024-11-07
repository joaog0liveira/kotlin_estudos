package ifgoiano.urt.produtosrecycler2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import ifgoiano.urt.produtosrecycler2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var produtos: List<Produtos>? = emptyList()
    private lateinit var adapter: ProdutosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // botao de voltar
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configura o recycler
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        // obtendo a lista de produtos
        produtos = ProdutosData.getProdutos()

        // configurando o adapter e o clickListener
        adapter = ProdutosAdapter(this, produtos, onClickProdutos())
        recyclerView.adapter = adapter
    }

    private fun onClickProdutos(): ProdutosAdapter.ProdutosOnClickListener {
        return object : ProdutosAdapter.ProdutosOnClickListener {
            override fun onClickProdutos(holder: ProdutosAdapter.ProdutosViewHolder?, idx: Int) {
                val produto = (produtos ?: emptyList())[idx]
                Toast.makeText(this@MainActivity, "Produtos: ${produto.nome}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}