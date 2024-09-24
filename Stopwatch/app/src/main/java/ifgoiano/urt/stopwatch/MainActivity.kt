package ifgoiano.urt.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ifgoiano.urt.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private var execucao = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.timerText.text
        runTimer()
    }

    fun onClickStart(view: View) {
        execucao = true
    }

    fun onClickStop(view: View) {
        execucao = false
    }

    fun onClickReset(view: View) {
        execucao = false
        seconds = 0

        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60

        binding.timerText.text = String.format("%d:%02d:%02d", hours, minutes, secs)
    }

    fun runTimer() {
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                binding.timerText.text = time

                if (execucao) {
                    seconds++
                }

                handler.postDelayed(this, 1000)
            }
        })
    }
}