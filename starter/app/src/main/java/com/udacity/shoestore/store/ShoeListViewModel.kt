package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel: ViewModel() {

    private val _shoeList = MutableLiveData<String>()
    val shoeList: LiveData<String>
        get() = _shoeList

    init {

    }
}