package com.example.cupcakeorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class OrderViewModel: ViewModel() {

    private var _price = MutableLiveData<Int>()
    val price: LiveData<Int> = _price

    private val _pickUpDate = MutableLiveData<String>()
    val pickUpDate: LiveData<String> = _pickUpDate

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _dateList = MutableLiveData<List<String>>(listOf())
    val dateList: LiveData<List<String>> = _dateList



    init {
        resetData()
    }



    fun resetData(){
        _quantity.value = 0
        _flavor.value = ""
        _price.value = 0
        _dateList.value = listOf()
    }

    fun getNextSevenDaysDate(): List<String> {
        val formatter = DateTimeFormatter.ofPattern("MM-dd")
        val tempList = mutableListOf<String>()

        for (i in 0..6) {
            val date = LocalDate.now().plusDays(i.toLong())
            val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
            tempList.add("$dayOfWeek, ${date.format(formatter)}")
        }

        _dateList.value = tempList
        return tempList
    }

    fun updatePrice(){
        var calculatePrice  = (quantity.value ?: 0) * 2

        if(_pickUpDate.value.equals(_dateList.value?.get(0))){
            calculatePrice += 3
        }
        _price.value = calculatePrice
    }


    fun setFlavor(newFlavor: String) {
        _flavor.value = newFlavor
    }

    fun setPrice (newPrice: Int){
        _price.value = newPrice
    }

    fun setQuantity(newQuantity: Int){
        _quantity.value = newQuantity
    }

    fun setPickUpDate(currentData: String){
        _pickUpDate.value = currentData
    }

}