package ifgoiano.urt.enviamensagem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val digitaText = findViewById<EditText>(R.id.digitaText)
        val enviar = findViewById<Button>(R.id.enviarBtn)

        enviar.setOnClickListener{
            val menssagem = digitaText.text.toString()
            val intent = Intent(this, MensagemActivity::class.java)
            intent.putExtra("msg", menssagem)
            startActivity(intent)
        }
    }
}