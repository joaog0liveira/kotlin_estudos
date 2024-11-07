package ifgoiano.urt.produtosrecycler2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdutosAdapter(
    private val context: Context,
    private val produtos: List<Produtos>?,
    private val onClickListener: ProdutosOnClickListener
) : RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder>() {


    interface ProdutosOnClickListener {
        fun onClickProdutos(holder: ProdutosViewHolder?, idx: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.adapter_produtos,
            parent, false
        )
        return ProdutosViewHolder(view)
    }

    override fun getItemCount(): Int = produtos!!.size

    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
        val produtos = produtos!![position]
        holder.nome.text = produtos.nome
        holder.img.setImageResource(produtos.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("nome", produtos.nome)
            intent.putExtra("img", produtos.img)
            intent.putExtra("descricao", produtos.descricao)
            intent.putExtra("preco", produtos.preco)
            context.startActivity(intent)
        }
    }

    inner class ProdutosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.imgProduto)
        var nome = itemView.findViewById<TextView>(R.id.nomeProduto)
    }
}