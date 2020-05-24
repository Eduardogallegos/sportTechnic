package mx.egs.sporttechnic

import PosenetActivity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_reproducir_video.*

class RealTime_Pose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_time__pose)
        configurarInterfaz()

        val posenetFrag = PosenetActivity()
        supportFragmentManager.beginTransaction().replace(R.id.modelContenedor, posenetFrag).commit()
    }

    private fun configurarInterfaz(){
        val nombreEx = intent.getStringExtra("nombreEx")
        val instrucciones = intent.getStringExtra("instrucciones")
        tvNombre.text = nombreEx

        if (nombreEx == "Lagartijas") {
            tvDescrip.text = instrucciones
            val uri1 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.pushup)
        }else if (nombreEx == "Fondos"){
            tvDescrip.text = instrucciones
            val uri2 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.dips)
        }else if (nombreEx == "Sentadillas"){
            tvDescrip.text = instrucciones
            val uri3 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.squat)
        }else if (nombreEx == "Desplantes"){
            tvDescrip.text = instrucciones
            val uri4 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.desplante)
        }else if (nombreEx == "Abdominales"){
            tvDescrip.text = instrucciones
            val uri5 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.abs)
        }else if (nombreEx == "Plancha"){
            tvDescrip.text = instrucciones
            val uri6 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.plank)
        } else if (nombreEx == "Saltar la cuerda"){
            tvDescrip.text = instrucciones
            //val uri7 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.pull)
            //playAnimacion(videoView1, uri7)
        }else if (nombreEx == "Burpees"){
            tvDescrip.text = instrucciones
            val uri8 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.burpee)
        }

    }
}
