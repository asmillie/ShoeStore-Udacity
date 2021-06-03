package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.store.models.Shoe
import timber.log.Timber

class ShoeListViewModel: ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<List<Shoe>> = Transformations.map(_shoeList) { shoeList ->
        shoeList.toList()
    }

    private val _shoeSaved = MutableLiveData<Boolean>(false)
    val shoeSaved: LiveData<Boolean>
        get() = _shoeSaved

    init {

    }

    fun add(shoe: Shoe) {
        // TODO: Resolve bug with null pointer
        Timber.i("Saving Shoe: ${shoe.toString()}")
        _shoeList.value!!.add(shoe)
        _shoeSaved.value = true
    }

    fun resetShoeSaved() {
        _shoeSaved.value = false
    }
}