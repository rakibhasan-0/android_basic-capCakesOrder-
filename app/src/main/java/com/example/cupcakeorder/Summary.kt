package com.example.cupcakeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.cupcakeorder.databinding.FragmentSummaryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class Summary : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()
    private lateinit var binding: FragmentSummaryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flavorSummary.text = viewModel.flavor.value
        binding.pickUpSummary.text = viewModel.pickUpDate.value
        binding.summaryPrice.text = "Total Price ${viewModel.price.value.toString()}"


            binding.summarySubmitButton.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Order Successful")
                    .setMessage("Congratulations! Your order has been placed.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        findNavController().navigate(R.id.action_summary_to_home2)
                    }
                    .show()
            }



        binding.cancelButton.setOnClickListener{
            viewModel.resetData()
            findNavController().navigate(R.id.action_summary_to_home2)
        }
    }

}