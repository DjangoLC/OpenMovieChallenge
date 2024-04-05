package com.example.openmovie.presentation.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmovie.data.datasource.remote.FirestoreRepository
import com.example.openmovie.data.datasource.remote.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val repository: FirestoreRepository) : ViewModel() {

    private val _locations = MutableLiveData<Set<Location>>()
    val locations: LiveData<Set<Location>>
        get() = _locations

    init {
        getLocations()
    }

    private fun getLocations() {
        viewModelScope.launch {
            repository.getLocations {
                _locations.value = it
            }
        }
    }

}