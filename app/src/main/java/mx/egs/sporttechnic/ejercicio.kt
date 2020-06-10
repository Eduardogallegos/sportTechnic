package mx.egs.sporttechnic

class ejercicio (val nombre: String, val descripcion: String, val img: String, val instrucciones: String, val tutorial: String) {

    companion object{
        val arrEjerciciosBrazo = arrayOf(
            ejercicio("Lagartijas",
                "Corrige tu técnica al hacer lagartijas",
                "pushup",
                "Acuéstate boca abajo, " +
                        "con las manos apoyadas en el piso a la altura de los hombros," +
                        "empuja al suelo para poder levantarte",
                "QiYpvU5FxgY"),
            ejercicio( "Fondos",
                "Corrige tu técnica al hacer fondos",
                "dips",
                "Colocar las manos sobre un escalon, " +
                        "pies paralelos, descender unicamente con los brazos, " +
                        "al tocar el suelo con los gluteos subir",
                "guC64ssjt48")
        )
        val arrEjerciciosPierna = arrayOf(
            ejercicio("Sentadillas",
                "Corrige tu técnica al hacer sentadillas",
                "squat","Pies a la altura de los hombros, " +
                        "descenso lento, sin que las rodillas pasen las puntas de los pies, " +
                        "al romper el paralelo, subir rápido",
                "KftjaZ8Cylw"),
            ejercicio( "Desplantes",
                "Corrige tu técnica al hacer Desplantes",
                "halfsquat","Pies abiertos en tijera uno atras, otro adelante, " +
                        "descenso lento, sin que las rodilla delantera pase la punta del pie, " +
                        "al romper el paralelo, subir rápido",
                "vH39yG4UCAA")
        )
        val arrEjerciciosAbdomen = arrayOf(
            ejercicio("Abdominales",
                "Corrige tu técnica al hacer abdominales",
                "situp","Acostado en el suelo, flexiona tus rodillas " +
                        "el torso sube y desciende lento ",
                "06AkfzTWRE4"),
            ejercicio( "Plancha",
                "Corrige tu técnica al hacer planchas",
                "planks","Posición de lagartija, " +
                        "mantener espalda, cadera y rodillas rectas",
                "YFwmkJDWKns")
        )
        val arrEjerciciosCardio = arrayOf(
            ejercicio("Saltar la cuerda",
                "Corrige tu técnica al saltar la cuerda",
                "rope_jump","Pies juntos, codos pegados al cuerpo, " +
                        "lo único que se mueve son las muñecas",
                "NUWwmexNlzM"),
            ejercicio( "Burpees",
                "Corrige tu técnica al hacer burpees",
                "burpee","Descendemos a posición de lagartija, " +
                        "pecho toca el suelo, nos reincoorporamos y hacemos un salto.",
                "Lb3vErt4TK4")
        )
    }

}