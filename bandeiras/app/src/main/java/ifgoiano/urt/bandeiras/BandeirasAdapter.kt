package ifgoiano.urt.bandeiras

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BandeirasAdapter(
    private val context: Context,
    private val bandeiras: List<Bandeiras>?,
    private val onClickListener: BandeirasOnClickListener
) :
    RecyclerView.Adapter<BandeirasAdapter.BandeirasViewHolder>() {


    interface BandeirasOnClickListener {
        fun onClickBandeiras(holder: BandeirasViewHolder?, idx: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            BandeirasViewHolder {

        val view = LayoutInflater.from(context).inflate(
            R.layout.adapter_estados,
            viewGroup, false
        )
        return BandeirasViewHolder(view)
    }

    override fun getItemCount(): Int = bandeiras!!.size

    override fun onBindViewHolder(holder: BandeirasViewHolder, position: Int) {
        val bandeiras = bandeiras!![position]
        holder.tNome.text = bandeiras.nome
        holder.img.setImageResource(bandeiras.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("nome", bandeiras.nome)
            intent.putExtra("capital", bandeiras.capital)
            intent.putExtra("populacao", bandeiras.populacao)
            intent.putExtra("regiao", bandeiras.regiao)
            intent.putExtra("img", bandeiras.img)
            context.startActivity(intent)
        }
    }

    class BandeirasViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var tNome: TextView = view.findViewById(R.id.nomeEstado)
        var img: ImageView = view.findViewById(R.id.imgBandeira)
    }

}