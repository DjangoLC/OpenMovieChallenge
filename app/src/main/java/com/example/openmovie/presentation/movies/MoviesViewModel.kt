package com.example.openmovie.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmovie.data.MovieRepository
import com.example.openmovie.presentation.movies.adapter.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    private val _topRateMovies = MutableLiveData<List<Movie>>()
    val topRateMovies: LiveData<List<Movie>>
        get() = _topRateMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies

    private val _banner = MutableLiveData<String>()
    val banner: LiveData<String>
        get() = _banner

    init {
        getPopularMovies()
        getRecommendedMovies()
        getTopRate()

    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = repository.getPopularMovies()
            popularMovies.value?.lastOrNull()?.let {
                _banner.value = "https://image.tmdb.org/t/p/w780${it.url}"
            }
        }
    }

    private fun getTopRate() {
        viewModelScope.launch {
            _topRateMovies.value = repository.getTopRate()
        }
    }

    private fun getRecommendedMovies() {
        viewModelScope.launch {
            _upcomingMovies.value = repository.getRecommendedMovies()
        }
    }

}