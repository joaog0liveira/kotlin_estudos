package ifgoiano.urt.bandeiras

data class Bandeiras(val nome: String, val img: Int, val capital: String, val populacao: String, val regiao: String)

object BandeirasData {
    fun getBandeiras(): List<Bandeiras> {
        return listOf(
            Bandeiras("Acre", R.drawable.acre, "Rio Branco", "881.935", "Norte"),
            Bandeiras("Alagoas", R.drawable.alagoas, "Maceió", "3.351.543", "Nordeste"),
            Bandeiras("Amapá", R.drawable.amapa, "Macapá", "845.731", "Norte"),
            Bandeiras("Amazonas", R.drawable.amazonas, "Manaus", "4.207.714", "Norte"),
            Bandeiras("Bahia", R.drawable.bahia, "Salvador", "14.873.064", "Nordeste"),
            Bandeiras("Ceará", R.drawable.ceara, "Fortaleza", "9.132.078", "Nordeste"),
            Bandeiras("Distrito Federal", R.drawable.distrito, "Brasília", "3.055.149", "Centro-Oeste"),
            Bandeiras("Espírito Santo", R.drawable.espiritosanto, "Vitória", "4.064.052", "Sudeste"),
            Bandeiras("Goiás", R.drawable.goias, "Goiânia", "7.113.540", "Centro-Oeste"),
            Bandeiras("Maranhão", R.drawable.maranhao, "São Luís", "7.075.181", "Nordeste"),
            Bandeiras("Mato Grosso", R.drawable.matogrosso, "Cuiabá", "3.526.220", "Centro-Oeste"),
            Bandeiras("Mato Grosso do Sul", R.drawable.matogrossosul, "Campo Grande", "2.809.394", "Centro-Oeste"),
            Bandeiras("Minas Gerais", R.drawable.minasgerais, "Belo Horizonte", "21.292.666", "Sudeste"),
            Bandeiras("Pará", R.drawable.para, "Belém", "8.690.745", "Norte"),
            Bandeiras("Paraíba", R.drawable.paraiba, "João Pessoa", "4.039.277", "Nordeste"),
            Bandeiras("Paraná", R.drawable.parana, "Curitiba", "11.516.840", "Sul"),
            Bandeiras("Pernambuco", R.drawable.pernambuco, "Recife", "9.616.621", "Nordeste"),
            Bandeiras("Piauí", R.drawable.piaui, "Teresina", "3.281.480", "Nordeste"),
            Bandeiras("Rio de Janeiro", R.drawable.riodejaneiro, "Rio de Janeiro", "17.366.189", "Sudeste"),
            Bandeiras("Rio Grande do Norte", R.drawable.riograndedonorte, "Natal", "3.534.165", "Nordeste"),
            Bandeiras("Rio Grande do Sul", R.drawable.riograndedosul, "Porto Alegre", "11.422.973", "Sul"),
            Bandeiras("Rondônia", R.drawable.rondonia, "Porto Velho", "1.796.460", "Norte"),
            Bandeiras("Roraima", R.drawable.roraima, "Boa Vista", "631.181", "Norte"),
            Bandeiras("Santa Catarina", R.drawable.santacatarina, "Florianópolis", "7.252.502", "Sul"),
            Bandeiras("São Paulo", R.drawable.saopaulo, "São Paulo", "46.289.333", "Sudeste"),
            Bandeiras("Sergipe", R.drawable.sergipe, "Aracaju", "2.318.822", "Nordeste"),
            Bandeiras("Tocantins", R.drawable.tocatins, "Palmas", "1.590.248", "Norte")

        )
    }
}
