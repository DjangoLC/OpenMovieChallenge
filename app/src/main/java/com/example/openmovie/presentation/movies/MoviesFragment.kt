package com.example.openmovie.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.openmovie.R
import com.example.openmovie.databinding.FragmentMoviesBinding
import com.example.openmovie.presentation.movies.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private val mostPopularMoviesAdapter = MovieAdapter()
    private val topRatedMoviesAdapter = MovieAdapter()
    private val upcomingMoviesAdapter = MovieAdapter()

    private val viewmodel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, null, false)
        binding.lifecycleOwner = this
        binding.banner = viewmodel.banner
        binding.mostPopularAdapter = mostPopularMoviesAdapter
        binding.topRatedMoviesAdapter = topRatedMoviesAdapter
        binding.recommendedMoviesAdapter = upcomingMoviesAdapter
        binding.recommendedHeader = getString(R.string.recommended_movies)
        binding.mostPopularHeader = getString(R.string.most_popular_movies)
        binding.topRatedHeader = getString(R.string.top_rated_movies)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewmodel.popularMovies.observe(viewLifecycleOwner) {
            mostPopularMoviesAdapter.submitList(it)
        }
        viewmodel.topRateMovies.observe(viewLifecycleOwner) {
            topRatedMoviesAdapter.submitList(it)
        }
        viewmodel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingMoviesAdapter.submitList(it)
        }
    }

}