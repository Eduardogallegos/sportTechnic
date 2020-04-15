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
        val extras = intent.extras
        val video: Uri = Uri.parse(extras?.getString("videoUri"))
        val videoView = findViewById<VideoView>(R.id.videoView)

        playVideo(videoView, video)


        btnRegresar.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

    }

    private fun playVideo(videoView: VideoView, uri: Uri){
        val controller = MediaController(this)
        videoView.setMediaController(controller)
        controller.setMediaPlayer(videoView)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
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






