package com.example.cupcakeorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cupcakeorder.databinding.FragmentDateBinding
import com.example.cupcakeorder.databinding.FragmentFlavorBinding
import java.time.LocalDate


class Date : Fragment() {

    private var binding: FragmentDateBinding? = null
    private val viewModel: OrderViewModel by activityViewModels()

    private lateinit var daysList: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daysList = viewModel.getNextSevenDaysDate()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDateBinding.inflate(inflater,container,false)

        // populate the radio button with information which we stored in daysList
        daysList.forEach{ day->
            val radioButton = RadioButton(context)
            radioButton.text = day
            binding?.radioGroup?.addView(radioButton)
        }

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.price.observe(viewLifecycleOwner, Observer { newPrice ->
            binding?.flavorPrice?.text = "Total Price: $newPrice"
        })

        binding?.radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            val selectedDate = radioButton.text.toString()
            viewModel.setPickUpDate(selectedDate)
            viewModel.updatePrice()
        }
        binding?.flavorSubmitButton?.setOnClickListener{
            findNavController().navigate(R.id.action_date_to_summary)
        }
    }






}