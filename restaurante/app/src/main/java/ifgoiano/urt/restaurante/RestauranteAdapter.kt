package ifgoiano.urt.restaurante

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestauranteAdapter (

    private val restaurantes: MutableList<Restaurante>,

    private val onItemClickListener: (Restaurante) -> Unit) :
RecyclerView.Adapter<RestauranteAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNome: TextView = view.findViewById(R.id.tvNome)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val resturante = restaurantes[position]
                    onItemClickListener(resturante)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_restaurante, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurante = restaurantes[position]
        holder.tvNome.text = restaurante.nome
        holder.ratingBar.rating = restaurante.classificacao
    }

    override fun getItemCount(): Int = restaurantes.size
}