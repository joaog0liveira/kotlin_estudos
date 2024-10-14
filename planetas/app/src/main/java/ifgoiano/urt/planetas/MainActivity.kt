package ifgoiano.urt.planetas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.planetas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener{

    private lateinit var listView: ListView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = binding.listView

        val planetas = PlanetaData.getPlanetas()

        listView.adapter = PlanetaAdapter(this, planetas)

        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, idx: Int, id: Long) {
        val planeta = parent.getItemAtPosition(idx) as Planeta
        Toast.makeText(this, "Planeta:${planeta.nome}", Toast.LENGTH_SHORT).show()
    }

}