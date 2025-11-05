package viewModels

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModels: ViewModel() {
    private val _countent = mutableIntStateOf(value = 0)
    val counter = _countent

    fun add() {
        _countent.value++
    }
}