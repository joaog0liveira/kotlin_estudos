package ifgoiano.urt.restaurante

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestauranteAdapter
    private lateinit var fab: FloatingActionButton
    private val restaurantes = mutableListOf<Restaurante>()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = RestauranteAdapter(resturantes) { resturantes ->
            atualizaRestaurantes(resturantes)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        setupSwipeToDelete()

        loadRestaurantes()
    }

    private fun loadRestaurantes() {
        firestore.collection("restaurantes")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("MainActivity",
                        "Erro ao escutar mudanças no Firestore", e)
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    restaurantes.clear()

                    for (document in snapshots) {
                        val restaurante = document.toObject(
                            Restaurante::class.java).copy(id = document.id)
                        restaurantes.add(restaurante)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
    }

    private fun atualizaRestaurantes(restaurante: Restaurante) {
        val intent = Intent(this, EditarRestauranteActivity::class.java)
        intent.putExtra("id", restaurante.id)
        intent.putExtra("nome", restaurante.nome)
        intent.putExtra("classificacao", restaurante.classificacao)
        startActivity(intent)
    }

    fun inserirRestaurantes(view: View) {
        val intent = Intent(this, EditarRestauranteActivity::class.java)
        startActivity(intent)
    }

    private fun excluirRestaurante(docId: String, position: Int) {
        firestore.collection("restaurantes").document(docId)
            .delete()
            .addOnSuccessListener {
                restaurantes.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .addOnFailureListener { e ->
                Log.w("MainActivity", "Erro ao excluir restaurante", e)
            }

    }

    private fun setupSwipeToDelete() {

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val restaurante = restaurantes[position]
                excluirRestaurante(restaurante.id, position)
            }
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                // Desenha o botão de exclusão conforme o item é deslizado
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val paint = Paint()
                    paint.color = Color.RED
                    // Desenha um retângulo vermelho atrás do item deslizado
                    val background = RectF(
                        itemView.right.toFloat() + dX,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat()
                    )
                    c.drawRect(background, paint)
                    // Desenha o ícone de lixeira (opcional)
                    val icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_launcher_foreground)
                    icon?.setBounds(
                        itemView.right - 100,
                        itemView.top + (itemView.height - 100) / 2,
                        itemView.right - 50,
                        itemView.top + (itemView.height + 100) / 2
                    )
                    icon?.draw(c)
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


}