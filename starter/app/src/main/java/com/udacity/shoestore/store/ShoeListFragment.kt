package com.udacity.shoestore.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.store.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        initButton()
        initObservables()

        return binding.root
    }

    private fun initButton() {
        binding.addShoeFab.setOnClickListener {
            val shoe = Shoe("", 0.0, "", "")

            // Navigate to Shoe Detail
            findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(shoe)
            )
        }
    }

    private fun initObservables() {
        viewModel.shoeList.observe(viewLifecycleOwner, { shoes ->
            // Build shoe list
            for (shoe in shoes) {
                Timber.i("Looping through shoes")
                addShoeView(shoe)
            }
        })
    }

    private fun addShoeView(shoe: Shoe) {
        Timber.i("Adding view for shoe ${shoe.name}")
        val shoeText = TextView(context)
        shoeText.text = shoe.name
        shoeText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        binding.shoeList.addView(shoeText)
    }
}