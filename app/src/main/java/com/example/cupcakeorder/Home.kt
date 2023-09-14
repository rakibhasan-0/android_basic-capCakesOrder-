package com.example.cupcakeorder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cupcakeorder.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class Home : Fragment() {

    private var binding : FragmentHomeBinding? = null
    private val viewModel: OrderViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.submitButton?.setOnClickListener {

            val input = binding?.userInput?.text.toString().trim()

            // Check if the input is empty or contains non-numeric characters
            if (input.isEmpty() || !input.matches(Regex("\\d+"))) {
                // Show a dialog asking the user to insert a correct number
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Invalid Input")
                    .setMessage("Please insert a valid number.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                viewModel.setQuantity(input.toInt())

                Log.d("quantity ","${viewModel.quantity.value}")
                findNavController().navigate(R.id.action_home2_to_flavor)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the View Binding reference to avoid memory leaks
        binding = null
    }

}