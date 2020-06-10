package mx.egs.sporttechnic

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActiv : AppCompatActivity(), ListenerRecyclerGrupos{

    private var AdaptadorGrupos : adaptadorGrupos? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        configurarRecycler()
        auth = FirebaseAuth.getInstance()

        checarInicio()
    }

    private fun checarInicio() {
        val inicio = intent.getStringExtra("INICIO")
        if (inicio == "true"){
            advertencia()
        }
    }

    fun agradecimientos(view: View){
        startActivity(Intent(this, agradecimientosActiv::class.java))
    }

    fun calendar(view: View){
        startActivity(Intent(this, CalendarActivity::class.java))
    }

    private fun advertencia(){
        val dialogo = AlertDialog.Builder(this)
        dialogo.setMessage("Esta app fue desarrollada con el objetivo de ayudar a correción de" +
         " técnicas de ejercicio, no debe tomarse como guía definitiva. Consulta a un experto.")
            .setCancelable(false)
            .setTitle("Advertencia")
            .setNegativeButton("Aceptar", object : DialogInterface.OnClickListener {
                override fun onClick(dialogo: DialogInterface?, p1: Int) {
                    dialogo?.dismiss()
                }
            })
        val alerta = dialogo.create()
        alerta.show()
    }

    private fun configurarRecycler() {
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerGrupos.layoutManager = layout

        AdaptadorGrupos = adaptadorGrupos(this, grupoMuscular.arrGruposGenerales)
        AdaptadorGrupos?.listenerGrupos = this
        recyclerGrupos.adapter = AdaptadorGrupos

        val divisor = DividerItemDecoration(this, layout.orientation)
        recyclerGrupos.addItemDecoration(divisor)
    }

    override fun onBackPressed() {
    }

    override fun itemClicked(position: Int) {
        val intRecyclerEjercicios = Intent(this, EjerciciosPorGrupo::class.java)
        val grupo = AdaptadorGrupos?.arrGrupos?.get(position)?.nombre
        intRecyclerEjercicios.putExtra("GRUPO", grupo)
        startActivity(intRecyclerEjercicios)
    }

    fun showUser(view: View){
        startActivity(Intent(this, DisplayUsers::class.java))
    }
}
