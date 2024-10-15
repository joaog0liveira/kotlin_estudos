package ifgoiano.urt.bandeiras

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ifgoiano.urt.bandeiras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private var bandeiras: List<Bandeiras>? = emptyList()
    private lateinit var adapter: BandeirasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ação para exibir o botão de voltar na ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Configura o RecyclerView

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.setHasFixedSize(true)

        // Obtenha a lista de bandeiras
        bandeiras = BandeirasData.getBandeiras()

        // Configura o Adapter e o click listener

        adapter = BandeirasAdapter(this, bandeiras, onClickBandeiras())
        recyclerView.adapter = adapter
    }

    private fun onClickBandeiras(): BandeirasAdapter.BandeirasOnClickListener {
        return object : BandeirasAdapter.BandeirasOnClickListener {
            override fun onClickBandeiras(holder: BandeirasAdapter.BandeirasViewHolder?, idx: Int)
            {
                val bandeira = (bandeiras ?: emptyList())[idx]
                Toast.makeText(this@MainActivity, "Bandeiras: ${bandeira.nome}", Toast.LENGTH_LONG).show()
            }
        }
    }

}

