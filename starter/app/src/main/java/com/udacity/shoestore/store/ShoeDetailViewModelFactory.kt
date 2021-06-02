package com.udacity.shoestore.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.store.models.Shoe
import java.lang.IllegalArgumentException

class ShoeDetailViewModelFactory(private val shoe: Shoe): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeDetailViewModel::class.java)) {
            return ShoeDetailViewModel(shoe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}