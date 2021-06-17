package com.udacity.shoestore.store

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.store.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private val viewModel: ShoeListSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        initButton()
        initObservables()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
            )

        }

        return super.onOptionsItemSelected(item)
    }

    private fun initButton() {
        binding.addShoeFab.setOnClickListener {
            // Navigate to Shoe Detail
            findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            )
        }
    }

    private fun initObservables() {
        viewModel.shoeList.observe(viewLifecycleOwner, { shoes ->
            Timber.i("Observed change to shoe list. Shoe List contains ${shoes.count()} items")
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
        shoeText.text = "${shoe.name}: Size ${shoe.size}, Company: ${shoe.company}, Description: ${shoe.description}"
        shoeText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        binding.shoeList.addView(shoeText)
    }
}