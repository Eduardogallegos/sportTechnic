package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActiv : AppCompatActivity(), ListenerRecyclerGrupos{

    var AdaptadorGrupos : adaptadorGrupos? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        configurarRecycler()
        auth = FirebaseAuth.getInstance()
    }

    fun cerrarSesion(view: View){
        auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun calendar(view: View){
        startActivity(Intent(this, CalendarActivity::class.java))
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
