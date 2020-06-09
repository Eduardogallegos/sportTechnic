package mx.egs.sporttechnic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.ListFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 */
class TrainingFragment : ListFragment() {

    private  lateinit var trainingArr: MutableList<String>
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainingArr = mutableListOf()
        user = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        downladTrainings()
    }

    private fun downladTrainings() {
        val baseDatos = FirebaseDatabase.getInstance()
        val userId = user.currentUser?.uid
        val referencia = baseDatos.getReference("/Trainings/$userId")
        referencia.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Falla al descargar los entrenamientos", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                trainingArr.clear()
                for (registro in snapshot.children){
                    Log.i("BD", "$registro")
                    val training = registro.getValue(Training::class.java)
                    trainingArr.add("Entrenamiento:    ${training?.date} - ${training?.hour}")
                }
                val adaptador = ArrayAdapter<String>(context!!,
                    android.R.layout.simple_expandable_list_item_1, trainingArr)
                listAdapter = adaptador
            }

        })
    }

}
