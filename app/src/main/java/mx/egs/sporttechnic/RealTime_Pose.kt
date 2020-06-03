package mx.egs.sporttechnic

import PosenetActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        }else if (nombreEx == "Fondos"){
            tvDescrip.text = instrucciones
        }else if (nombreEx == "Sentadillas"){
            tvDescrip.text = instrucciones
        }else if (nombreEx == "Desplantes"){
            tvDescrip.text = instrucciones
        }else if (nombreEx == "Abdominales"){
            tvDescrip.text = instrucciones
        }else if (nombreEx == "Plancha"){
            tvDescrip.text = instrucciones
        } else if (nombreEx == "Saltar la cuerda"){
            tvDescrip.text = instrucciones
        }else if (nombreEx == "Burpees"){
            tvDescrip.text = instrucciones
        }

    }
}
