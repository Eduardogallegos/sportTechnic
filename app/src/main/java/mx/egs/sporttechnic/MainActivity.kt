package mx.egs.sporttechnic

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.provider.Settings
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

        auth = FirebaseAuth.getInstance()

        probarWifi(this)

    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this, MenuActiv::class.java)
            intent.putExtra("INICIO", "true")
            startActivity(intent)
            finish()
        }
    }

    private fun probarWifi(context:Context){
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (!isConnected){
            prenderWifi()
        }
    }

    private fun prenderWifi(){
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
                    val intent = Intent(this, MenuActiv::class.java)
                    intent.putExtra("INICIO", "true")
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext, "Falla en autenticación como invitado," +
                            " compruebe su conexión a internet.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}