package com.example.openmovie.presentation.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.openmovie.R
import com.example.openmovie.data.datasource.remote.ProfileRepository
import com.example.openmovie.databinding.FragmentProfileBinding
import com.example.openmovie.presentation.movies.adapter.MovieAdapter
import com.example.openmovie.utils.hasLocationPermission
import com.example.openmovie.utils.hasStoragePermission
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.InputStream

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val favoriteMoviesAdapter = MovieAdapter()
    private val ratedMoviesAdapter = MovieAdapter()
    private val viewmodel: ProfileVieModel by viewModels()

    //Move logic to a delegate and the use i tin the viewmodel
    private val mActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
            uris.mapNotNull { getInputStreamFromUri(it, requireContext()) }.forEach { input ->
                viewmodel.uploadFile(System.currentTimeMillis().toString(), input)
            }
        }

    private fun getInputStreamFromUri(contentUri: Uri, context: Context): InputStream? {
        return try {
            context.contentResolver.openInputStream(contentUri)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, null, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.favoriteAdapter = favoriteMoviesAdapter
        binding.ratedHAdapter = ratedMoviesAdapter
        binding.ratedHeader = getString(R.string.rated_movies)
        binding.favoriteHeader = getString(R.string.favorite_movies)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUploadFiles.setOnClickListener {
            launchActivityForResult()
        }
        observe()
    }

    private fun observe() {
        viewmodel.account.observe(viewLifecycleOwner) {
            binding.tvUserName.text = it.username
        }
        viewmodel.ratedMovies.observe(viewLifecycleOwner) {
            ratedMoviesAdapter.submitList(it)
        }
        viewmodel.favoriteMovies.observe(viewLifecycleOwner) {
            favoriteMoviesAdapter.submitList(it)
        }
    }

    private fun launchActivityForResult() {
        //TODO("HANDLE STORAGE PERMISSION PROPERLY")
        if (requireContext().hasStoragePermission()) {
            mActivityResultLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            Toast.makeText(requireContext(), "should grant storage permissions from settings", Toast.LENGTH_SHORT).show()
        }
    }

}