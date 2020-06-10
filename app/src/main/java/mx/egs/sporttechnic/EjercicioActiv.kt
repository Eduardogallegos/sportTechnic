package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_ejercicio.*

class EjercicioActiv : AppCompatActivity(){

    private var link = "dQw4w9WgXcQ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio)

        configurarInterfaz()

        player.getPlayerUiController().showFullscreenButton(true)
        player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(@NonNull youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                youTubePlayer.cueVideo(link, 0f)
            }
        })
        player.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
            if (player.isFullScreen()){
                player.exitFullScreen()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

                if (supportActionBar != null){
                    supportActionBar!!.show()
                }
            }else{
                player.enterFullScreen()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

                if (supportActionBar != null){
                    supportActionBar!!.hide()
                }
            }
        })
    }



    private fun configurarInterfaz() {
        val nombreEjercicio = intent.getStringExtra("WORKOUT")
        val instruccion = intent.getStringExtra("instrucciones")
        val tutorial = intent.getStringExtra("tutorial")
        tvNombreEjercicio.text = nombreEjercicio

        if (tutorial != null){
            when (nombreEjercicio){
                "Lagartijas" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Fondos" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Sentadillas" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Desplantes" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Abdominales" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Plancha" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Saltar la cuerda" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
                "Burpees" -> {
                    tvDescripEjercicio.text = instruccion
                    link = tutorial
                }
            }
        }
    }

    private fun getExName(intent2: Intent) {
        val nombreEjercicio = intent.getStringExtra("WORKOUT")
        intent2.putExtra("nombreEx",nombreEjercicio)
        val instruccion = intent.getStringExtra("instrucciones")
        intent2.putExtra("instrucciones", instruccion)
    }

    fun posenet(view: View){
        val intent = Intent(this, RealTime_Pose::class.java)
        getExName(intent)
        startActivity(intent)
    }
}
