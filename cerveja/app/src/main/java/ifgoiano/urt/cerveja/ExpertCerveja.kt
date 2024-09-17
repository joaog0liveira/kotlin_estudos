package ifgoiano.urt.cerveja

class ExpertCerveja {

    fun getMarcas(tipoCerveja: String): List<String> {
        return when (tipoCerveja) {
            "Pilsen" -> listOf("Marca 1", "Marca 3", "Marca 6")
            "Puro Malte" -> listOf("Marca 2", "Marca 5", "Marca 7")
            "Weissbier" -> listOf("Marca 3", "Marca 4")
            "IPA" -> listOf("Marca 4", "Marca 9", "Marca 10")
            "APA" -> listOf("Marca 5", "Marca 1")
            else -> listOf("Nenhuma marca disponivel")
        }
    }
}