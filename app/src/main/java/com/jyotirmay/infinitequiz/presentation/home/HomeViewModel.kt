package com.jyotirmay.infinitequiz.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.domain.usecases.GetCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCountry: GetCountry): ViewModel() {

    var state = mutableStateOf(listOf<CountryResponse>())
        private set

    fun fetchCountry() {
        viewModelScope.launch {
            try {
                val countryList = getCountry.invoke()
                countryList.collect{response ->
                    state.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}