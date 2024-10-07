package ifgoiano.urt.stopwatch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
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
    private var estavaEmExecucao = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            execucao = savedInstanceState.getBoolean("execucao")
//            execucao = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.menuButton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
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

    override fun onPause() {
        super.onPause()
        estavaEmExecucao = execucao
        execucao = false
    }
    override fun onStop() {
        super.onStop()
        estavaEmExecucao = execucao
        execucao = false
    }

//    override fun onRestart() {
//        super.onRestart()
//    }

    override fun onStart() {
        super.onStart()
        if (estavaEmExecucao) {
            execucao = true
        }
    }

    override fun onResume() {
        super.onResume()
        if (estavaEmExecucao) {
            execucao = true
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("execucao", execucao)
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



// teste de thread dentro de thread

//    private fun runTimer() {
//        Thread {
//            while (execucao) {
//                Thread.sleep(1000)
//                seconds++
//
//                runOnUiThread {
//                    val hours = seconds / 3600
//                    val minutes = (seconds % 3600) / 60
//                    val secs = seconds % 60
//                    val time = String.format("%d:%02d:%02d", hours, minutes, secs)
//                    binding.timerText.text = time
//                }
//            }
//        }.start()
//    }

