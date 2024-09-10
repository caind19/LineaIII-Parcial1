/*
    Parcial I
    Cain David Martinez Cardona
    Línea de Profuncización III
    901N

    Sistema para elección de representante y visibilización de costos de campaña del municipio Premier
    Voto Municipio Premier
 */

/*
    Función main... Permite a través de un when inmerso dentro de un ciclo while generar el menu de opciones permanente
    para gestionar las elecciones hasta que se elija la opción salir para finalizar el aplicativo.
 */

fun main() {
    val elecciones = Elegir() // Instancia de la clase Elegir que contiene los nombres de los candidatos
    while (true) {
        println("\n|||_____________________Menú_____________________|||")
        println("|| 1. Votar por un candidato                      ||")
        println("|| 2. Mostrar costo de campaña                    ||")
        println("|| 3. Vaciar todas las urnas                      ||")
        println("|| 4. Ver número total de votos                   ||")
        println("|| 5. Porcentaje de votos por candidato           ||")
        println("|| 6. Costo promedio de campaña en las elecciones ||")
        println("|| 7. Ver Candidato ganador                       ||")
        println("|| 8. Salir                                       ||")
        print("\nSeleccione una opción:>> ")

        // Manejo de entrada para selección de menú
        when (readLine()?.trim()) {
            "1" -> votar(elecciones.candidatos)
            "2" -> mostrarCosto(elecciones.candidatos)
            "3" -> vaciarUrnas(elecciones.candidatos)
            "4" -> totalVotos(elecciones.candidatos)
            "5" -> porcentajeVoto(elecciones.candidatos)
            "6" -> costoPromedio(elecciones.candidatos)
            "7" -> verGanador(elecciones.candidatos)
            "8" -> {
                println("Saliendo del sistema.")
                return
            }
            else -> println("Opción no permitida. Vuelva a intentarlo.")
        } // Muestra error por ingreso de valores no válidos
    }
}


/*
    Función votar... Permite a un usuario votar por un candidato y especificar
    el medio publicitario que incentivó su voto
 */

private fun votar(candidatos: List<Candidato>) {
    println("\nSeleccione un candidato para votar:")
    candidatos.forEachIndexed { index, candidato -> println("${index + 1}. ${candidato.nombre}") }

    val candidatoIndex = try {
        readLine()?.toInt()?.minus(1) ?: throw IllegalArgumentException("Debe ingresar un número.")
    } catch (e: Exception) {
        println("Entrada no válida. Debe seleccionar un número de candidato.")
        return
    } // Captura de error por ingreso de valores no válidos

    if (candidatoIndex !in candidatos.indices) {
        println("Candidato no válido.")
        return
    } // Captura de error por ingreso de valores no válidos

    println("Mencione el medio de publicidad que incentivó su voto: (1) Internet, (2) Radio, (3) TV)")
    val medio = try {
        readLine()?.toInt()
    } catch (e: Exception) {
        println("Entrada no válida. Debe ingresar un número.")
        return
    } // Captura de error por ingreso de valores no válidos

    when (medio) {
        1 -> candidatos[candidatoIndex].votosInternet++
        2 -> candidatos[candidatoIndex].votosRadio++
        3 -> candidatos[candidatoIndex].votosTV++
        else -> println("Medio no válido.")
    }
}

/*
    Función mostrarCostos... Muestra los costos de campaña por cada candidato.
 */
private fun mostrarCosto(candidatos: List<Candidato>) {
    println("\nCostos de campaña por candidato:")
    candidatos.forEach {
        println("${it.nombre}: \$${it.costoCampaña}")
    }
}

/*
    Función vaciarUrnas... Reinicia todos los votos de todos los candidatos.
 */
private fun vaciarUrnas(candidatos: List<Candidato>) {
    candidatos.forEach {
        it.votosInternet = 0
        it.votosRadio = 0
        it.votosTV = 0
    }
    println("Las urnas han sido vaciadas en su totalidad.")
}

/*
    Función totalVotos... Muestra el número total de votos de todos los candidatos.
    Conectada con "candidatos" a través de la lista Candidato.
 */
private fun totalVotos(candidatos: List<Candidato>) {
    val total = candidatos.sumOf { it.totalVotos }
    println("\nTotal de votos: $total")
}

/*
    Función porcentajeVotos... Muestra el porcentaje de votos por cada candidato.
    conectada con "candidatos" a través de la lista Candidato.
 */
private fun porcentajeVoto(candidatos: List<Candidato>) {
    val totalVotos = candidatos.sumOf { it.totalVotos }
    if (totalVotos > 0) {
        println("\nPorcentaje de votos por candidato:")
        candidatos.forEach {
            val porcentaje = (it.totalVotos.toDouble() / totalVotos) * 100
            println("${it.nombre}: %.2f%%".format(porcentaje))
        }
    } else {
        println("No se hallaron votos registrados.")
    }
}

/*
    Función costoPromedio... Calcula y muestra el costo promedio por voto en la campaña.
 */
private fun costoPromedio(candidatos: List<Candidato>) {
    val totalCosto = candidatos.sumOf { it.costoCampaña }
    val totalVotos = candidatos.sumOf { it.totalVotos }
    if (totalVotos > 0) {
        val promedio = totalCosto / totalVotos
        println("\nEl costo promedio de campaña es \$${"%.2f".format(promedio)} por voto.")
    } else {
        println("No se hallaron votos registrados.")
    }
}

/*
    Función verGanador... Determina y muestra el candidato ganador basado en el total de votos.
 */
private fun verGanador(candidatos: List<Candidato>) {
    val ganador = candidatos.maxByOrNull { it.totalVotos }
    if (ganador != null && ganador.totalVotos > 0) {
        println("\nEl candidato ganador es: ${ganador.nombre} con ${ganador.totalVotos} votos.")
    } else {
        println("No se hallaron votos registrados.")
    }
}
