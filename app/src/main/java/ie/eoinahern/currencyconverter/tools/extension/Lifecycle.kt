package ie.eoinahern.currencyconverter.tools.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ie.eoinahern.currencyconverter.domain.exception.Failure


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, callback: (T) -> Unit) {
    liveData.observe(this, Observer(callback))
}

fun <L : LiveData<Failure>> LifecycleOwner.fail(liveData: L, callback: (Failure) -> Unit) {
    liveData.observe(this, Observer(callback))
}




