/*
    Clase Candidato que representa a cada candidato con sus votos influenciados por diferentes medios.
 */
data class Candidato(val nombre: String, var votosInternet: Int = 0, var votosRadio: Int = 0, var votosTV: Int = 0) {
    // Calcula el total de votos recibidos por el candidato
    val totalVotos: Int
        get() = votosInternet + votosRadio + votosTV

    // Calcula el costo total de la campaña en función de los votos y los medios que influyeron
    val costoCampaña: Double
        get() = votosInternet * 700000.0 + votosRadio * 200000.0 + votosTV * 600000.0
}
