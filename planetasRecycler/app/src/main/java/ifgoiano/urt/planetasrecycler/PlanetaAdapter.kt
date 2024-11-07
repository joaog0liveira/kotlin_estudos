package ifgoiano.urt.planetasrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlanetaAdapter(
    private val context: Context,
    private val planetas: List<Planeta>,
    private val onClickListener: PlanetaOnClickListener
) : RecyclerView.Adapter<PlanetaAdapter.PlanetasViewHolder>() {
    interface PlanetaOnClickListener {
        fun onClickPlaneta(holder: PlanetasViewHolder?, idx: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlanetasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_planeta, parent, false)

        return PlanetasViewHolder(view)
    }

    override fun getItemCount(): Int = planetas!!.size

    override fun onBindViewHolder(holder: PlanetasViewHolder, position: Int) {
        val planeta = planetas!![position]

        holder.tNome.text = planeta.nome
        holder.img.setImageResource(planeta.img)

        holder.itemView.setOnClickListener {
            onClickListener.onClickPlaneta(holder, position)
        }
    }

    class PlanetasViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tNome: TextView = view.findViewById(R.id.tNome)
        var img: ImageView = view.findViewById(R.id.img)
    }
}