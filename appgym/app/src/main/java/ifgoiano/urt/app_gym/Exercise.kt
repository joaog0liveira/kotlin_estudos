package ifgoiano.urt.app_gym

data class Exercise(val name: String, val description: String)

object ExerciseList {
    fun getExerciseList(): List<Exercise> {
        return listOf(
            Exercise(
                "Supino", "Exercício para peitoral utilizando barra ou halteres.\nFazer 3 séries de 15."),
            Exercise(
                "Agachamento", "Exercício de força para pernas e glúteos.\nFazer 4 séries de 12."),
            Exercise(
                "Flexão", "Exercício de peso corporal para peitoral e tríceps.\nFazer 4 séries de 12."),
            Exercise(
                "Prancha", "Exercício de isometria para fortalecer o core.\nFazer 3 séries de 1 minuto."),
            Exercise(
                "Rosca Direta", "Exercício de bíceps utilizando barra ou halteres.\nFazer 3 séries de 15.")
        )
    }
}
