package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        if (name != "" && password != "" && password2 != "" && birthday != "" && mail != "" && gender != ""){
            if(password.length >= 6){
                if (password == password2){
                    registrarCuenta(mail, password, birthday, name, gender)
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

    private fun registrarCuenta(mail: String, password:String, birthday: String, name: String, gender: String) {
        auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                var Fuid = auth.currentUser?.uid
                var DBReference = baseDatos.getReference("/Users/$Fuid")
                if(Fuid != null){
                    val user = Usuario(Fuid, mail, birthday, name, gender)
                    DBReference.setValue(user).addOnCompleteListener(this){task2 ->
                        if(task2.isSuccessful){
                            Toast.makeText(this, "Cuenta creada para $mail", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MenuActiv::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "No se pudo registrar el usuario en la base de datos", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this, "No se pudo registrar la cuenta para este usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
