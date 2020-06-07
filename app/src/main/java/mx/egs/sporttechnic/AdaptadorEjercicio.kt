package mx.egs.sporttechnic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.renglon_ejercicio.view.*

class AdaptadorEjercicio (private val contexto: Context, var arrEjercicios: Array<ejercicio>):
    RecyclerView.Adapter<AdaptadorEjercicio.RenglonEjercicio>() {

    var listenerEjercicios : ListenerRecyclerGrupos? = null
    inner class RenglonEjercicio (var vistaRenglonExe : View): RecyclerView.ViewHolder(vistaRenglonExe)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonEjercicio {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.renglon_ejercicio, parent, false)
        return RenglonEjercicio(vista)
    }

    override fun getItemCount(): Int {
        return arrEjercicios.size
    }

    override fun onBindViewHolder(holder: RenglonEjercicio, position: Int) {
        val ejercicio = arrEjercicios[position]
        val id = contexto.resources.getIdentifier("mx.egs.sporttechnic:drawable/"+ ejercicio.img, null, null)
        holder.vistaRenglonExe.imgEjercicio.setImageResource(id)
        holder.vistaRenglonExe.tvEjercicio.text = ejercicio.nombre
        holder.vistaRenglonExe.tvDescripcionEjercicio.text = ejercicio.descripcion

        holder.vistaRenglonExe.setOnClickListener{
            listenerEjercicios?.itemClicked(position)
        }
    }
}