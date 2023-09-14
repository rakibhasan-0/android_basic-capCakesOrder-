package com.example.cupcakeorder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cupcakeorder.databinding.FragmentFlavorBinding


class Flavor : Fragment() {

    private var binding: FragmentFlavorBinding? = null
    private val viewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlavorBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.flavorSubmitButton?.setOnClickListener {
            Log.d("CURRENT PRICE","${viewModel.quantity.value}")
            val selectedId = binding?.flavorButton?.checkedRadioButtonId


            if (selectedId != null && selectedId != -1) {
                val radioButton: RadioButton = binding?.flavorButton?.findViewById(selectedId) ?: return@setOnClickListener
                val name: String = radioButton.text.toString()
                viewModel.setFlavor(name)
                findNavController().navigate(R.id.action_flavor_to_date)
            } else {
                Toast.makeText(context, "Please select a flavor", Toast.LENGTH_SHORT).show()
            }
        }
    }



}