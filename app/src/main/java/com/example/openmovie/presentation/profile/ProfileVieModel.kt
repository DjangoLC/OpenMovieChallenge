package com.example.openmovie.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmovie.data.datasource.remote.FirestoreRepository
import com.example.openmovie.data.datasource.remote.ProfileRepository
import com.example.openmovie.presentation.movies.adapter.Account
import com.example.openmovie.presentation.movies.adapter.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ProfileVieModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account>
        get() = _account

    private val _ratedMovies = MutableLiveData<List<Movie>>()
    val ratedMovies: LiveData<List<Movie>>
        get() = _ratedMovies

    private val _favoriteMovies = MutableLiveData<List<Movie>>()
    val favoriteMovies: LiveData<List<Movie>>
        get() = _favoriteMovies

    init {
        getAccount()
        getRatedMovies()
        getFavoriteMovies()
    }

    private fun getAccount() {
        viewModelScope.launch {
            profileRepository.getAccountInfo()?.let {
                _account.value = it
            }
        }
    }

    private fun getRatedMovies() {
        viewModelScope.launch {
            profileRepository.getRatedMovies()?.let {
                _ratedMovies.value = it
            }
        }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            profileRepository.getFavoriteMovies()?.let {
                _favoriteMovies.value = it
            }
        }
    }

    fun uploadFile(name: String, inputStream: InputStream) {
        firestoreRepository.uploadFile(name, inputStream) {
            //handle logic for UploadFileState a queue could be useful for a retry option
            //files could be hold in a queue while uploaded and everytime a files is successfully upload
            //then remove it form the queue, remaining files can be retry.
        }
    }

}