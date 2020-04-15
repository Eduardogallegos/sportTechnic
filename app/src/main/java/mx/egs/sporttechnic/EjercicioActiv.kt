package mx.egs.sporttechnic

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
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
            tv.text = "Acuéstate boca abajo, " +
                    "con las manos apoyadas en el piso a la altura de los hombros," +
                    " empuja al suelo para poder levantarte"
            imgWorkout.setImageResource(R.drawable.pushup)
        }else if (nombreEjercicio == "Fondos"){
            tv.text = "Colorar las manos sobre un escalon, " +
                    "pies paralelos, descender unicamente con los brazos, " +
                    "al tocar el suelo con los gluteos subir"
            imgWorkout.setImageResource(R.drawable.dips)
        }else if (nombreEjercicio == "Sentadillas"){
            tv.text = "Pies a la altura de los hombros, " +
                    "descenso lento, sin que las rodillas pasen las puntas de los pies, " +
                    "al romper el paralelo, subir rápido"
            imgWorkout.setImageResource(R.drawable.squat)
        }else if (nombreEjercicio == "Desplantes"){
            tv.text = "Pies abiertos en tijera uno atras, otro adelante, " +
                    "descenso lento, sin que las rodilla delantera pase la punta del pie, " +
                    "al romper el paralelo, subir rápido"
            imgWorkout.setImageResource(R.drawable.halfsquat)
        }else if (nombreEjercicio == "Abdominales"){
            tv.text = "Acostado en el suelo, flexiona tus rodillas " +
                    "el torso sube y desciende lento "
            imgWorkout.setImageResource(R.drawable.situp)
        }else if (nombreEjercicio == "Plancha"){
            tv.text = "Colodo en posición de lagartija, " +
                    "mantener espalda, cadera y rodillas rectas"
            imgWorkout.setImageResource(R.drawable.pushup)
        } else if (nombreEjercicio == "Saltar la cuerda"){
            tv.text = "Pies juntos, codos pegados al cuerpo, " +
                    "lo único que se mueve son las muñecas"
            imgWorkout.setImageResource(R.drawable.rope_jump)
        }else if (nombreEjercicio == "Burpees"){
            tv.text = "Descendemos a posición de lagartija, " +
                    "pecho toca el suelo, nos reincoorporamos y hacemos un salto."
            imgWorkout.setImageResource(R.drawable.burpee)
        }

    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

    private fun dispatchGalleryIntent(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_GALLERY_ACCESS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            val videoUri: Uri? = intent?.data
            val intent = Intent(this, reproducirVideo::class.java)
            intent.putExtra("videoUri", videoUri.toString())
            startActivity(intent)
        }
        else if (requestCode == REQUEST_GALLERY_ACCESS && resultCode == Activity.RESULT_OK){
            val videoUri: Uri? = intent?.data
            val intent = Intent(this, reproducirVideo::class.java)
            intent.putExtra("videoUri", videoUri.toString())
            startActivity(intent)
        }
    }

    fun buttonVideo(view: View){
        dispatchTakeVideoIntent()
    }

    fun buttonGallery(view: View){
        dispatchGalleryIntent()
    }

    companion object {
        const val REQUEST_VIDEO_CAPTURE = 1
        const val REQUEST_GALLERY_ACCESS = 2
        const val PERMISSIONS = 101
    }
}
