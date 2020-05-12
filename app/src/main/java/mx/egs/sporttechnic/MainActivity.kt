package mx.egs.sporttechnic

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        probarWifi()
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            startActivity(Intent(this, MenuActiv::class.java))
            finish()
        }
    }

    fun probarWifi(){
        val dialogo = AlertDialog.Builder(this)
        dialogo.setMessage("La app requiere internet, ¿Quiere prender el Wi-fi?")
            .setCancelable(false)
            .setPositiveButton("Si", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))

                }
            })
            .setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialogo: DialogInterface?, p1: Int) {
                    dialogo?.dismiss()
                }
            })
        val alerta = dialogo.create()
        alerta.show()
    }

    fun createAccount(view: View) {
        startActivity(Intent(this, CreateAccountActiv::class.java))
    }

    fun signIn(view: View) {
        startActivity(Intent(this, LogInActiv::class.java))
    }

    fun signInAnonymously(view: View){
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MenuActiv::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Falla en autenticación como invitado," +
                            " compruebe su conexión a internet.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
