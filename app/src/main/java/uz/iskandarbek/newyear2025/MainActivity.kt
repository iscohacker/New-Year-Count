package uz.iskandarbek.newyear2025


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import uz.iskandarbek.newyear2025.databinding.ActivityMainBinding
import java.util.Calendar
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startCountdown()
    }

    private fun startCountdown() {
        // Yangi yil sanasi
        val newYearDate = Calendar.getInstance().apply {
            set(Calendar.MONTH, Calendar.DECEMBER)
            set(Calendar.DAY_OF_MONTH, 31)
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }.time

        handler.post(object : Runnable {
            override fun run() {
                val currentTime = Calendar.getInstance().time
                val diffInMillis = abs(newYearDate.time - currentTime.time)

                val seconds = (diffInMillis / 1000) % 60
                val minutes = (diffInMillis / (1000 * 60)) % 60
                val hours = (diffInMillis / (1000 * 60 * 60)) % 24
                val days = (diffInMillis / (1000 * 60 * 60 * 24)) % 7
                val weeks = (diffInMillis / (1000 * 60 * 60 * 24 * 7)) % 4
                val months = (diffInMillis / (1000 * 60 * 60 * 24 * 30))


                binding.oy.text = "$months oy"
                binding.hafta.text = "$weeks hafta"
                binding.kun.text = "$days kun"
                binding.soat.text = "$hours soat"
                binding.minut.text = "$minutes daqiqa"
                binding.sekund.text = "$seconds soniya"

                handler.postDelayed(this, 1000)
            }
        })
    }
}
