package mx.egs.sporttechnic

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity



class reproducirVideo : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproducir_video)
        val extras = intent.extras
        val video: Uri = Uri.parse(extras?.getString("videoUri"))

        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoURI(video)
        videoView.requestFocus()
        videoView.start()
    }

}




