package mx.egs.sporttechnic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ejercicio.*

class EjercicioActiv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio)

        configurarInterfaz()
    }

    private fun configurarInterfaz() {
        val nombreEjercicio = intent.getStringExtra("WORKOUT")
        tvNombreEjercicio.text = nombreEjercicio

        if (nombreEjercicio == "Lagartijas"){
            tvDescripEjercicio.text = "Acuéstate boca abajo, " +
                    "con las manos apoyadas en el piso a la altura de los hombros," +
                    " empuja al suelo para poder levantarte"
            imgWorkout.setImageResource(R.drawable.pushup)
        }else if (nombreEjercicio == "Fondos"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.dips)
        }else if (nombreEjercicio == "Sentadillas"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.pushup)
        }else if (nombreEjercicio == "Desplantes"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.halfsquat)
        }else if (nombreEjercicio == "Abdominales"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.pushup)
        }else if (nombreEjercicio == "Plancha"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.pushup)
        } else if (nombreEjercicio == "Saltar la cuerda"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.pushup)
        }else if (nombreEjercicio == "Burpees"){
            tvDescripEjercicio.text = "Agregar descripcion"
            imgWorkout.setImageResource(R.drawable.pushup)
        }

    }
}
