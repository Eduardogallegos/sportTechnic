package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_ejercicios_por_grupo.*

class EjerciciosPorGrupo : AppCompatActivity(), ListenerRecyclerGrupos {
    var adaptadorEjercicios : AdaptadorEjercicio? = null
    var arrEjercicios =  ejercicio.arrEjerciciosBrazo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_por_grupo)

        obtenerGrupo()
        configurarRecycler()
    }

    private fun obtenerGrupo() {
        val grupo = intent.getStringExtra("GRUPO")
        tvGrupoMuscular.text = grupo
        when (grupo){
            "Pierna" -> arrEjercicios = ejercicio.arrEjerciciosPierna
            "Abdomen" -> arrEjercicios = ejercicio.arrEjerciciosAbdomen
            "Cardio" -> arrEjercicios = ejercicio.arrEjerciciosCardio
        }
    }

    private fun configurarRecycler() {
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerEjercicios.layoutManager = layout

        adaptadorEjercicios = AdaptadorEjercicio(this, arrEjercicios)
        adaptadorEjercicios?.listenerEjercicios = this
        recyclerEjercicios.adapter = adaptadorEjercicios

        val divisor = DividerItemDecoration(this, layout.orientation)
        recyclerEjercicios.addItemDecoration(divisor)
    }

    override fun itemClicked(position: Int) {
        val intEjercicio = Intent(this, EjercicioActiv::class.java)
        val workout = adaptadorEjercicios?.arrEjercicios?.get(position)?.nombre
        intEjercicio.putExtra("WORKOUT", workout)
        val instrucciones = adaptadorEjercicios?.arrEjercicios?.get(position)?.instrucciones
        intEjercicio.putExtra("instrucciones", instrucciones)
        val tutorial = adaptadorEjercicios?.arrEjercicios?.get(position)?.tutorial
        intEjercicio.putExtra("tutorial", tutorial)
        startActivity(intEjercicio)
    }
}
