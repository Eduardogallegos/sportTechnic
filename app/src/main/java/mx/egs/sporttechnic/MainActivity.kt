package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListenerRecyclerGrupos {
    var AdaptadorGrupos : adaptadorGrupos? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configurarRecycler()
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
}
