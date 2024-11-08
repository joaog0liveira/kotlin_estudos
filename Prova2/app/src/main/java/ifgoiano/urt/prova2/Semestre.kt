package ifgoiano.urt.prova2

data class Semestre(
    val ano: Int,
    val semestre: String
)

object SemestreData {
    fun getSemestre(): List<Semestre> {
        return listOf(
            Semestre(2020, "1º semestre"),
            Semestre(2020, "2º semestre"),
            Semestre(2021, "1º semestre"),
            Semestre(2021, "2º semestre"),
            Semestre(2022, "1º semestre"),
            Semestre(2022, "2º semestre"),
            Semestre(2023, "1º semestre"),
            Semestre(2023, "2º semestre"),
            Semestre(2024, "1º semestre"),
            Semestre(2024, "2º semestre")
        )
    }
}