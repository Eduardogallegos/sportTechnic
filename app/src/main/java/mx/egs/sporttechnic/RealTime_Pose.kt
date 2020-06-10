package mx.egs.sporttechnic

import PosenetActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_real_time__pose.*

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

        when (nombreEx) {
            "Lagartijas" -> tvDescrip.text = instrucciones
            "Fondos" -> tvDescrip.text = instrucciones
            "Sentadillas" -> tvDescrip.text = instrucciones
            "Desplantes" -> tvDescrip.text = instrucciones
            "Abdominales" -> tvDescrip.text = instrucciones
            "Plancha" -> tvDescrip.text = instrucciones
            "Saltar la cuerda" -> tvDescrip.text = instrucciones
            "Burpees" -> tvDescrip.text = instrucciones
        }

    }

    override fun onBackPressed() {
    }

    fun regresarMenu(view:View){
        val intent = Intent(this, MenuActiv::class.java)
        intent.putExtra("INICIO", "false")
        startActivity(intent)
        finish()
    }
}
