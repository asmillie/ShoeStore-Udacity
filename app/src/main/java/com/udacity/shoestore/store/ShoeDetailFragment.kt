package com.udacity.shoestore.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.store.models.Shoe


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel: ShoeListSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        with(binding) {
            lifecycleOwner = this@ShoeDetailFragment
            viewModel = viewModel
            shoe = Shoe("", 0.0, "", "")
        }

        initObservables()
        initButtons()

        return binding.root
    }

    private fun initButtons() {
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
            )
        }
    }

    private fun initObservables() {
        viewModel.eventShoeSaved.observe(viewLifecycleOwner, Observer { eventShoeSaved ->
            if (eventShoeSaved == true) {
                viewModel.shoeSavedComplete()
                navigateToShoeList()
            }
        })
    }

    private fun navigateToShoeList() {
        findNavController().navigate(
            ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        )
    }
}