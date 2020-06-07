package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class agradecimientosActiv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agradecimientos)
    }

    fun regresarMenu(view: View){
        startActivity(Intent(this, MenuActiv::class.java))
        finish()
    }
}
