package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
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
        displayData()
    }

    private fun displayData(){
        val userId = auth.currentUser?.uid
        var DBReference = baseDatos.getReference("/Users/$userId")
        DBReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@DisplayUsers, "Falla al descargar los entrenamientos", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for (registro in snapshot.children){
                    when (registro.key){
                        "name" -> tvName.text = registro.value as CharSequence?
                        "gender" -> tvGender.text = registro.value as CharSequence?
                        "mail" -> tvMail.text = registro.value as CharSequence?
                        else -> tvBirthdate.text = registro.value as CharSequence?
                    }
                    /*tvName.text = registro.key()
                    tvGender.text = user?.gender
                    tvMail.text = user?.mail
                    tvBirthdate.text = user?.birthday
                    if(registro.key == userId){
                        Log.i("BD chida", "$registro")
                        val user = registro.getValue(Usuario::class.java)
                        tvName.text = user?.name
                        tvGender.text = user?.gender
                        tvMail.text = user?.mail
                        tvBirthdate.text = user?.birthday
                    }*/
                    Log.i("BD", "$registro")
                }
            }

        })

    }

    fun changePasword(view: View){
        val mail = auth.currentUser?.email
        if (mail != null) {
            auth.sendPasswordResetEmail(mail)
            Toast.makeText(this, "Se ha enviado un correo a $mail para restablecer la contraseña", Toast.LENGTH_LONG).show()
        }
    }

    fun cerrarSesion(view: View){
        auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
