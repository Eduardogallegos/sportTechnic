package mx.egs.sporttechnic

class grupoMuscular (val nombre: String, val descripcion: String, val img: String)
{
    companion object{
        val arrGruposGenerales = arrayOf(
            grupoMuscular("Brazo",
                "Trabaja todo el tren superior con estos ejercicios",
                "arm"),
            grupoMuscular( "Pierna",
                "Trabaja todo el tren inferior con estos ejercicios",
                "leg"),
            grupoMuscular("Abdomen",
                "Trabaja la zona media del cuerpo para lucir un abdomen definido",
                "abs"),
            grupoMuscular("Cardio",
                "Logra elevar tu ritmo cardiaco para quemar grasa",
                "cardio")
            )
    }
}