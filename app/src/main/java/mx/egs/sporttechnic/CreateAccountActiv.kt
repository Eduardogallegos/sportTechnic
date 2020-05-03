package mx.egs.sporttechnic

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActiv : AppCompatActivity() {

    private lateinit var baseDatos: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private var name = ""
    private var password = ""
    private var password2 = ""
    private var birthday = ""
    private var mail = ""
    private var gender = ""
    private  val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        baseDatos = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    fun createAccount(view: View){
        name = tiName.text.toString()
        password = etPassword.text.toString()
        password2 = etPassVerif.text.toString()
        birthday = etBirthday.text.toString()
        mail = etMail.text.toString()
        var radioButtonID = radioGroup.checkedRadioButtonId
        var radioButton:RadioButton = findViewById(radioButtonID)
        gender= radioButton.text as String

        Log.i(TAG, "Name: $name")
        Log.i(TAG, "Pass1: $password")
        Log.i(TAG, "Pass2: $password2")
        Log.i(TAG, "Birthday: $birthday")
        Log.i(TAG, "Mail: $mail")
        Log.i(TAG, "Gender: $gender")

        if (name != "" && password != "" && password2 != "" && birthday != "" && mail != ""  && gender != ""){
            if(password.length >= 6){
                if (password == password2){
                    registrarCuenta()
                }else{
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Verifica que todos los campos estén llenos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrarCuenta() {
        auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                sendEmailVerification()
                var Fuid = auth.currentUser?.uid
                var DBReference = baseDatos.getReference("/Users/$Fuid")
                Log.i(TAG, "Firebase uid: $Fuid")
                if(Fuid != null) {
                    val user = Usuario(mail, birthday, name, gender)
                    DBReference.setValue(user).addOnCompleteListener(this) { task2 ->
                        if (task2.isSuccessful) {
                            Toast.makeText(this, "Cuenta creada para $mail", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this, MenuActiv::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "No se pudo registrar el usuario en la base de datos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }else{
            Toast.makeText(this, "No se pudo registrar la cuenta para este usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext,
                        "Verifica tu direccion ${user.email} con el correo que te enviamos",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
