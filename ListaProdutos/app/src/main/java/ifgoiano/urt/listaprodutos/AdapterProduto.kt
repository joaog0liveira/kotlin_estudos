package ifgoiano.urt.listaprodutos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterProduto(private val context: Context,
    private val produtos: MutableList<Produto>) :
    RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(
            R.layout.hardware_item,
            parent,
            false
        )

        val holder = ProdutoViewHolder(itemLista)
        return holder;
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.foto.setImageResource(produtos[position].foto)
        holder.nome.text = produtos[position].nome
        holder.descricao.text = produtos[position].descricao
        holder.preco.text = produtos[position].preco
    }


    override fun getItemCount(): Int = produtos.size

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foto = itemView.findViewById<ImageView>(R.id.fotoProduto)
        var nome = itemView.findViewById<TextView>(R.id.nomeProduto)
        var descricao = itemView.findViewById<TextView>(R.id.descricaoProduto)
        var preco = itemView.findViewById<TextView>(R.id.precoProduto)
    }
}