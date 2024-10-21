package ifgoiano.urt.firebase

class Paises {

    var capital: String? = null
    var pais: String? = null
    var sigla: String? = null

    constructor()

    constructor(capital: String, pais: String, sigla: String) {
        this.capital = capital
        this.pais = pais
        this.sigla = sigla
    }


}