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
                "Corrige tu técnica al hacer lagartijas",
                "pushup"),
            ejercicio( "Desplantes",
                "Corrige tu técnica al hacer fondos",
                "halfsquat")
        )
        val arrEjerciciosAbdomen = arrayOf(
            ejercicio("Abdominales",
                "Corrige tu técnica al hacer lagartijas",
                "pushup"),
            ejercicio( "Plancha",
                "Corrige tu técnica al hacer fondos",
                "dips")
        )
        val arrEjerciciosCardio = arrayOf(
            ejercicio("Saltar la cuerda",
                "Corrige tu técnica al hacer lagartijas",
                "pushup"),
            ejercicio( "Burpees",
                "Corrige tu técnica al hacer fondos",
                "dips")
        )
    }

}