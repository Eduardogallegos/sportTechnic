package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.set
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_display_users.*
import javax.xml.validation.Validator

class DisplayUsers : AppCompatActivity() {
    private lateinit var baseDatos: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_users)

        auth = FirebaseAuth.getInstance()
        baseDatos = FirebaseDatabase.getInstance()
    }

    fun displayData(){

    }


    fun cerrarSesion(view: View){
        auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
