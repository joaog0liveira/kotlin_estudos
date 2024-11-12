package ifgoiano.urt.climamvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClimaViewModel : ViewModel() {

    private val climaRepository = ClimaRepository()

    private val _climaData = MutableLiveData<Clima>()
    val climaData: LiveData<Clima> get() = _climaData

    fun atualizarClima() {
        val clima = climaRepository.getClimaData()
        _climaData.value = clima
    }

}