package ifgoiano.urt.planetasrecycler

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import ifgoiano.urt.planetasrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var planetas: List<Planeta>? = emptyList()
    private lateinit var adapter: PlanetaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // botao de voltar na action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configurando o recycler view
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        planetas = PlanetaData.getPlanetas()

        adapter = PlanetaAdapter(this, planetas!!, onClickPlaneta())
        recyclerView.adapter = adapter
    }


    // funcao que retornar o listener para os clicks nos planetas
    private fun onClickPlaneta(): PlanetaAdapter.PlanetaOnClickListener {
        return object : PlanetaAdapter.PlanetaOnClickListener {
            override fun onClickPlaneta(holder: PlanetaAdapter.PlanetasViewHolder?, idx: Int) {

                val planeta = (planetas ?: emptyList()) [idx]
                Toast.makeText(this@MainActivity, "Planeta: ${planeta.nome}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}