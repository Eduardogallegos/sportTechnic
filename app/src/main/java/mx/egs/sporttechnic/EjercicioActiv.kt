package mx.egs.sporttechnic

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_ejercicio.*

class EjercicioActiv : AppCompatActivity(){

    private var link = "dQw4w9WgXcQ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio)

        configurarInterfaz()

        //YouTube
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
        tvNombreEjercicio.text = nombreEjercicio

        if (nombreEjercicio == "Lagartijas"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }else if (nombreEjercicio == "Fondos"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }else if (nombreEjercicio == "Sentadillas"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }else if (nombreEjercicio == "Desplantes"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }else if (nombreEjercicio == "Abdominales"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }else if (nombreEjercicio == "Plancha"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        } else if (nombreEjercicio == "Saltar la cuerda"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }else if (nombreEjercicio == "Burpees"){
            tvDescripEjercicio.text = instruccion
            link = "dQw4w9WgXcQ"
        }
    }


    private fun getExName(intent2: Intent) {
        val nombreEjercicio = intent.getStringExtra("WORKOUT")
        intent2.putExtra("nombreEx",nombreEjercicio)
        val instruccion = intent.getStringExtra("instrucciones")
        intent2.putExtra("instrucciones", instruccion)
    }


    fun buttonGallery(view: View){
        val intent = Intent(this, RealTime_Pose::class.java)
        getExName(intent)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_GALLERY_ACCESS = 2
    }


}
