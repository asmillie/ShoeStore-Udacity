package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.store.models.Shoe
import timber.log.Timber

class MainActivityViewModel: ViewModel() {
    // List of Shoes
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<List<Shoe>> = Transformations.map(_shoeList) { shoeList ->
        shoeList.toList()
    }
    // Index of Selected Shoe
    private val _shoeIndex = MutableLiveData<Int>(0)
    val shoeIndex: LiveData<Int>
        get() = _shoeIndex
    // Event - Shoe Saved
    private val _eventShoeSaved = MutableLiveData<Boolean>(false)
    val eventShoeSaved: LiveData<Boolean>
        get() = _eventShoeSaved

    init {
        _shoeList.value = mutableListOf()
    }

    fun addShoe(shoe: Shoe) {
        Timber.i("Saving Shoe: ${shoe.toString()}")
        _shoeList.value!!.add(shoe)
    }

    fun shoeSavedComplete() {
        _eventShoeSaved.value = false
    }
}