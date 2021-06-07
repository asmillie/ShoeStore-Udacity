package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.store.models.Shoe
import timber.log.Timber

class ShoeListSharedViewModel: ViewModel() {
    // List of Shoes
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<List<Shoe>> = Transformations.map(_shoeList) { shoeList ->
        shoeList.toList()
    }

    // Event - Shoe Saved
    private val _eventShoeSaved = MutableLiveData<Boolean>(false)
    val eventShoeSaved: LiveData<Boolean>
        get() = _eventShoeSaved

    init {
        Timber.i("Initializing View Model")
        _shoeList.value = mutableListOf()
    }

    fun addShoe(shoe: Shoe) {
        Timber.i("Saving Shoe: ${shoe.toString()}")
        _shoeList.value!!.add(shoe)
        _eventShoeSaved.value = true
    }

    fun shoeSavedComplete() {
        _eventShoeSaved.value = false
    }
}