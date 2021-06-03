package com.udacity.shoestore.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.store.models.Shoe


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private lateinit var viewModelFactory: ShoeDetailViewModelFactory
    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.lifecycleOwner = this

        val shoeDetailFragmentArgs by navArgs<ShoeDetailFragmentArgs>()

        viewModelFactory = ShoeDetailViewModelFactory(shoeDetailFragmentArgs.shoe)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeDetailViewModel::class.java)

        binding.shoeViewModel = viewModel

        initButtons()

        return binding.root
    }

    private fun initButtons() {
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(null))
        }

        binding.saveButton.setOnClickListener {
            findNavController().navigate(getShoeAction())
        }
    }

    private fun getShoeAction(): NavDirections {
        val shoe = Shoe(
            binding.nameInput.text.toString(),
            binding.sizeInput.text.toString().toDouble(),
            binding.companyInput.text.toString(),
            binding.descriptionInput.text.toString()
        )

        return ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(shoe)
    }
}