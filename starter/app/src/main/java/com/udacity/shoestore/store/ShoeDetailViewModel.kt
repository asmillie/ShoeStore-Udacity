package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.store.models.Shoe

class ShoeDetailViewModel(shoe: Shoe): ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _eventShoeSaved = MutableLiveData<Boolean>()
    val eventShoeSaved: LiveData<Boolean>
        get() = _eventShoeSaved

    init {
        _shoe.value = shoe
        _eventShoeSaved.value = false
    }

    fun onShoeSaved() {
        _eventShoeSaved.value = true
    }

    fun onShoeSavedComplete() {
        _eventShoeSaved.value = false
    }
}