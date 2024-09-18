package ifgoiano.urt.cerveja

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class oBuscarCervejaActivity : AppCompatActivity() {

    private val expertCerveja = ExpertCerveja()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //referenciar o spinner e textview
        val spinnerCerveja: Spinner = findViewById(R.id.spinnerCerveja)
        val textViewMarcas: TextView = findViewById(R.id.textViewMarcas)

        // carregar os tipos de cerveja
        val tipoDeCerveja = resources.getStringArray(R.array.tipos_cerveja)

        // criando adapter para o spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            tipoDeCerveja
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCerveja.adapter = adapter

        spinnerCerveja.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                // busca o tipo de cerveja selecionado
                val tipoSelecionado = parent.getItemAtPosition(position).toString()

                // busca as marcas
                val marcas = expertCerveja.getMarcas(tipoSelecionado)

                textViewMarcas.text = marcas.joinToString(", ")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                null
            }
        }
    }
}