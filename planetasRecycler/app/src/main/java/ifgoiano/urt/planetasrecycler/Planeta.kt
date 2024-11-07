package ifgoiano.urt.planetasrecycler

data class Planeta(val nome: String, val img: Int)

object PlanetaData {
    fun getPlanetas(): List<Planeta> {
        return listOf(
            Planeta("Mercúrio", R.drawable.mercurio),
            Planeta("Vênus", R.drawable.venus),
            Planeta("Terra", R.drawable.terra),
            Planeta("Marte", R.drawable.marte),
            Planeta("Júpiter", R.drawable.jupiter),
            Planeta("Saturno", R.drawable.saturno),
            Planeta("Urano", R.drawable.urano),
            Planeta("Netuno", R.drawable.netuno),
            Planeta("Plutão", R.drawable.plutao)
        )
    }
}
