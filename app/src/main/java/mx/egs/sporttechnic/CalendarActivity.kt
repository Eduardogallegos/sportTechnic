package mx.egs.sporttechnic

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class CalendarActivity : AppCompatActivity() {

    private lateinit var baseDatos: FirebaseDatabase
    private lateinit var user: FirebaseAuth

    private lateinit var date: String
    private lateinit var hour: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        baseDatos = FirebaseDatabase.getInstance()
        user = FirebaseAuth.getInstance()

        val trainingsFrag= TrainingFragment()
        supportFragmentManager.beginTransaction().replace(R.id.contenedorFragmentos, trainingsFrag).commit()

    }

    fun newTrainingDate(view: View){
        val calendar = Calendar.getInstance()
        var dia = calendar.get(Calendar.DAY_OF_MONTH)
        var mes = calendar.get(Calendar.MONTH)
        var anio = calendar.get(Calendar.YEAR)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            date = "$dayOfMonth/$monthOfYear/$year"
            newTrainingHour()
        }, anio, mes, dia)
        dpd.show()
    }

    private fun newTrainingHour() {
        val calendar = Calendar.getInstance()
        var hora = calendar.get(Calendar.HOUR_OF_DAY)
        var minutos = calendar.get(Calendar.MINUTE)
        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{view, hourOfDay, minute ->
            hour = "$hourOfDay:$minute"
            newTraining()
        }, hora, minutos, false)
        tpd.show()
    }

    private fun newTraining() {
        val userId = user.currentUser?.uid
        val training = Training(date, hour)
        var DBReference = baseDatos.getReference("/Trainings/$userId").push()
        DBReference.setValue(training).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Entrenamiento guardado", Toast.LENGTH_SHORT)
                    .show()
                programNotification(date, hour)
            } else {
                Toast.makeText(
                    this,
                    "No se pudo registrar el entrenamiento",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun programNotification(dateString: String,timeString: String){
        val not = NotificationUtils()
        not.setNotification(Calendar.getInstance().timeInMillis, timeString, dateString, this)
    }

}
