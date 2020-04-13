package mx.egs.sporttechnic

class ejercicio (val nombre: String, val descripcion: String, val img: String) {

    companion object{
        val arrEjerciciosBrazo = arrayOf(
            ejercicio("Lagartijas",
                "Corrige tu técnica al hacer lagartijas",
                "pushup"),
            ejercicio( "Fondos",
                "Corrige tu técnica al hacer fondos",
                "dips")
        )
        val arrEjerciciosPierna = arrayOf(
            ejercicio("Sentadillas",
                "Corrige tu técnica al hacer sentadillas",
                "squat"),
            ejercicio( "Desplantes",
                "Corrige tu técnica al hacer Desplantes",
                "halfsquat")
        )
        val arrEjerciciosAbdomen = arrayOf(
            ejercicio("Abdominales",
                "Corrige tu técnica al hacer abdominales",
                "situp"),
            ejercicio( "Plancha",
                "Corrige tu técnica al hacer planchas",
                "pushup")
        )
        val arrEjerciciosCardio = arrayOf(
            ejercicio("Saltar la cuerda",
                "Corrige tu técnica al saltar la cuerda",
                "rope_jump"),
            ejercicio( "Burpees",
                "Corrige tu técnica al hacer burpees",
                "burpee")
        )
    }

}