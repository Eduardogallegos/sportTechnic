package mx.egs.sporttechnic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.renglon_grupos.view.*

class adaptadorGrupos (private val contexto: Context, var arrGrupos: Array<grupoMuscular>):
    RecyclerView.Adapter<adaptadorGrupos.RenglonGrupo>()
{
    var listenerGrupos : ListenerRecyclerGrupos? = null
    inner class RenglonGrupo (var vistaRenglon: View): RecyclerView.ViewHolder(vistaRenglon)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenglonGrupo {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.renglon_grupos, parent, false)
        return RenglonGrupo(vista)
    }

    override fun getItemCount(): Int {
        return arrGrupos.size
    }

    override fun onBindViewHolder(holder: RenglonGrupo, position: Int) {
        val grupo = arrGrupos[position]
        val id = contexto.resources.getIdentifier("mx.egs.sporttechnic:drawable/"+ grupo.img, null, null)
        holder.vistaRenglon.imgGrupo.setImageResource(id)
        holder.vistaRenglon.tvGrupo.text = grupo.nombre
        holder.vistaRenglon.tvDescripcionGrupo.text = grupo.descripcion

        holder.vistaRenglon.setOnClickListener{
            listenerGrupos?.itemClicked(position)
        }
    }
}