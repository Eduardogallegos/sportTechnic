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
        val instruccion = intent.getStringExtra("instrucciones")
        tvNombreEjercicio.text = nombreEjercicio

        if (nombreEjercicio == "Lagartijas"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.pushup)
        }else if (nombreEjercicio == "Fondos"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.dips)
        }else if (nombreEjercicio == "Sentadillas"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.squat)
        }else if (nombreEjercicio == "Desplantes"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.halfsquat)
        }else if (nombreEjercicio == "Abdominales"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.situp)
        }else if (nombreEjercicio == "Plancha"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.planks)
        } else if (nombreEjercicio == "Saltar la cuerda"){
            tvDescripEjercicio.text = instruccion
            imgWorkout.setImageResource(R.drawable.rope_jump)
        }else if (nombreEjercicio == "Burpees"){
            tvDescripEjercicio.text = instruccion
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
            val intent = Intent(this, RealTime_Pose::class.java)
            intent.putExtra("videoUri", videoUri.toString())
            getExName(intent)
            startActivity(intent)
        }
        else if (requestCode == REQUEST_GALLERY_ACCESS && resultCode == Activity.RESULT_OK){
            val videoUri: Uri? = intent?.data
            val intent = Intent(this, RealTime_Pose::class.java)
            intent.putExtra("videoUri", videoUri.toString())
            getExName(intent)
            startActivity(intent)
        }
    }

    private fun getExName(intent2: Intent) {
        val nombreEjercicio = intent.getStringExtra("WORKOUT")
        intent2.putExtra("nombreEx",nombreEjercicio)
        val instruccion = intent.getStringExtra("instrucciones")
        intent2.putExtra("instrucciones", instruccion)
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
