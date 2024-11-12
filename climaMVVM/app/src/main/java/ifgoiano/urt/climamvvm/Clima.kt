package ifgoiano.urt.climamvvm

data class Clima(
    val temperatura: Double,
    val umidade: Int,
    val condicoes: String
)

class ClimaRepository {
    fun getClimaData(): Clima {
        return Clima(25.5, 70, "Ensolarado")
    }
}
