package ifgoiano.urt.stopwatch

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ifgoiano.urt.stopwatch.databinding.ActivityTempBinding

class TempActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTempBinding
    private var temporizador: CountDownTimer? = null
    private var tempoSobrando: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTempBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timeOptions = arrayOf("10 segundos", "30 segundos", "1 minuto", "5 minutos", "10 minutos", "30 minutos", "1 hora")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, timeOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.timeSpinner.adapter = adapter

        binding.startButton.setOnClickListener {
            val selectedTime = binding.timeSpinner.selectedItem.toString()
            tempoSobrando = when (selectedTime) {
                "10 segundos" -> 10 * 1000
                "30 segundos" -> 30 * 1000
                "1 minuto" -> 1 * 60 * 1000
                "5 minutos" -> 5 * 60 * 1000
                "10 minutos" -> 10 * 60 * 1000
                "30 minutos" -> 30 * 60 * 1000
                "1 hora" -> 1 * 60 * 60 * 1000
                else -> 0
            }
            startTimer()
        }

        binding.resetButton.setOnClickListener {
            resetTimer()
        }

        binding.menuButton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        updateTimerText()
    }

    private fun startTimer() {
        temporizador?.cancel()

        temporizador = object : CountDownTimer(tempoSobrando, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempoSobrando = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                binding.timerTextView.text = "Tempo esgotado!"
            }
        }.start()
    }

    private fun resetTimer() {
        temporizador?.cancel()
        tempoSobrando = 0
        updateTimerText()
    }

    private fun updateTimerText() {
        val minutes = (tempoSobrando / 1000) / 60
        val seconds = (tempoSobrando / 1000) % 60

        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.timerTextView.text = timeFormatted
    }

    override fun onPause() {
        super.onPause()
        temporizador?.cancel()
    }
}


