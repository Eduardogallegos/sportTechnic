package mx.egs.sporttechnic

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reproducir_video.*



class reproducirVideo : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproducir_video)
        configurarInterfaz()


       // REPRODUCIR VIDEO OBTENIDO DE GALERIA O GRABADO
        val extras = intent.extras
        val video: Uri = Uri.parse(extras?.getString("videoUri"))
        val videoView = findViewById<VideoView>(R.id.videoView)
        playVideo(videoView, video)

        btnRegresar.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

    }

    private fun configurarInterfaz(){
        val nombreEx = intent.getStringExtra("nombreEx")
        val instrucciones = intent.getStringExtra("instrucciones")
        tvNombre.text = nombreEx
        val videoView1 = findViewById<VideoView>(R.id.videoEx)

        if (nombreEx == "Lagartijas") {
            tvDescrip.text = instrucciones
            val uri1 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.pushup)
            playAnimacion(videoView1, uri1)
        }else if (nombreEx == "Fondos"){
            tvDescrip.text = instrucciones
            val uri2 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.dips)
            playAnimacion(videoView1, uri2)
        }else if (nombreEx == "Sentadillas"){
            tvDescrip.text = instrucciones
            val uri3 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.squat)
            playAnimacion(videoView1, uri3)
        }else if (nombreEx == "Desplantes"){
            tvDescrip.text = instrucciones
            val uri4 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.desplante)
            playAnimacion(videoView1, uri4)
        }else if (nombreEx == "Abdominales"){
            tvDescrip.text = instrucciones
            val uri5 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.abs)
            playAnimacion(videoView1, uri5)
        }else if (nombreEx == "Plancha"){
            tvDescrip.text = instrucciones
            val uri6 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.plank)
            playAnimacion(videoView1, uri6)
        } else if (nombreEx == "Saltar la cuerda"){
            tvDescrip.text = instrucciones
            //val uri7 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.pull)
            //playAnimacion(videoView1, uri7)
        }else if (nombreEx == "Burpees"){
            tvDescrip.text = instrucciones
            val uri8 = Uri.parse("android.resource://mx.egs.sporttechnic/" + R.raw.burpee)
            playAnimacion(videoView1, uri8)

        }

    }
     // PLAY VIDEO OBTENIDO GALERIA
    private fun playVideo(videoView: VideoView, uri: Uri){
        val controller = MediaController(this)
        videoView.setMediaController(controller)
        controller.setMediaPlayer(videoView)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
    }

    // PLAY ANIMACION/TUTORIAL
    private fun playAnimacion(videoViewEx: VideoView, uri: Uri) {
        //val controller = MediaController(this)
       // videoViewEx.setMediaController(controller)
       // controller.setMediaPlayer(videoViewEx)
        videoViewEx.setVideoURI(uri)
        videoViewEx.requestFocus()
        //videoViewEx.setKeepScreenOn(true);
        videoViewEx.start()

    }


    private fun dispatchGalleryIntent(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, EjercicioActiv.REQUEST_GALLERY_ACCESS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == EjercicioActiv.REQUEST_GALLERY_ACCESS && resultCode == Activity.RESULT_OK) {
            val videoUri: Uri? = intent?.data
            if (videoUri != null) {
                playVideo(videoView,videoUri)
            }
        }
    }

    fun buttonGallery(view: View){
        dispatchGalleryIntent()
    }

    companion object {
        const val REQUEST_GALLERY_ACCESS = 2
        const val PERMISSIONS = 101
    }


}





