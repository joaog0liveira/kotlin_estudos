import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ifgoiano.urt.prova2.R
import ifgoiano.urt.prova2.Semestre

class SemestreAdapter(
    private val semestres: List<Semestre>,
    private val onSemestreClick: (Semestre) -> Unit
) : RecyclerView.Adapter<SemestreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_semestre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val semestre = semestres[position]
        holder.textView.text = "${semestre.semestre} ${semestre.ano}"

        holder.itemView.setOnClickListener {
            onSemestreClick(semestre)
        }
    }

    override fun getItemCount() = semestres.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewSemestre)
    }
}
