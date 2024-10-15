package ifgoiano.urt.bandeiras

data class Bandeiras(val nome: String, val img: Int)

object BandeirasData {
    fun getBandeiras(): List<Bandeiras> {
        return listOf(
            Bandeiras("Acre", R.drawable.acre),
            Bandeiras("Alagoas", R.drawable.alagoas),
            Bandeiras("Amapa", R.drawable.amapa),
            Bandeiras("Amazonas", R.drawable.amazonas),
            Bandeiras("Bahia", R.drawable.bahia),
            Bandeiras("Ceara", R.drawable.ceara),
            Bandeiras("Distrito Federal", R.drawable.distrito),
            Bandeiras("Espirito Santo", R.drawable.espiritosanto)

            )
    }
}
