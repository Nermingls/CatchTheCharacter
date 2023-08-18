package com.example.catchthecharacter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.catchthecharacter.databinding.ActivityMainBinding
import kotlin.random.Random as Random1

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var runnable = Runnable{}
    val handler =Handler(Looper.getMainLooper())
    var imageList = ArrayList<ImageView>()

    var skor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imageList.add(binding.character1)
        imageList.add(binding.character2)
        imageList.add(binding.character3)
        imageList.add(binding.character4)
        imageList.add(binding.character5)
        imageList.add(binding.character6)
        imageList.add(binding.character7)
        imageList.add(binding.character8)
        imageList.add(binding.character9)
        hideImages()

        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
            binding.TimeText.text = "Time : ${p0/1000}"
            }

            override fun onFinish() {
                binding.TimeText.text = "Time: 0"
                handler.removeCallbacks(runnable)

                for (image in imageList) {
                    image.visibility = View.INVISIBLE
                }
                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialog,witch->
                    //Restart
                    val intent = getIntent()
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }

                alert.show()
            }

        }.start()
    }
    fun hideImages(){

       runnable =  object : Runnable{
           override fun run() {
               for(image in imageList) {
                   image.visibility = View.INVISIBLE
               }
               val random = Random1
               val randomIndex = random.nextInt(9)
               imageList[randomIndex].visibility = View.VISIBLE
               handler.postDelayed(runnable,500)
           }
       }
        handler.post(runnable)
    }
    fun increaseskor(view: View){
        skor +=1
        binding.skorText.text = "Skor : ${skor}"
    }

}
