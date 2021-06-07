package com.udacity.shoestore.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.MainActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }
}