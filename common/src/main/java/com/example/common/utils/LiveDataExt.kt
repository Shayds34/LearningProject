package com.example.common.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}

suspend inline fun <T : Any?> MutableLiveData<out T>.offer(value: T) =
    withContext(Dispatchers.Main) {
        this@offer.value = value
    }

suspend fun <T> MutableLiveData<T>.setState(block: suspend (T) -> T) {
    val oldValue = this.value
    if (oldValue != null) {
        val newValue = block(oldValue)
        withContext(Dispatchers.Main) {
            this@setState.value = newValue
        }
    } else {
        Log.d("ViewModel SetState", "Using SetState on Nullable value")
    }
}
