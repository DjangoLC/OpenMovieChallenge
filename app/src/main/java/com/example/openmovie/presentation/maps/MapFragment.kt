package com.example.openmovie.presentation.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.openmovie.R
import com.example.openmovie.data.datasource.remote.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MapsViewModel by viewModels()
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = SupportMapFragment.newInstance()
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.add(R.id.map, mapFragment)
            ?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        observeLocations()
    }

    private fun observeLocations() {
        viewModel.locations.observe(viewLifecycleOwner) { locations ->
            if (locations.isNotEmpty()) {
                updateMapMarkers(locations)
            }
        }
    }

    private fun updateMapMarkers(locations: Set<Location>?) {
        googleMap?.let { map ->
            val builder = LatLngBounds.Builder()
            locations?.forEach { location ->
                val markerOptions = MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title("marker")
                map.addMarker(markerOptions)?.let { builder.include(it.position) }
            }
            val bounds = builder.build()
            val cameraUpdateFactory = CameraUpdateFactory.newLatLngBounds(bounds, 100)
            map.animateCamera(cameraUpdateFactory)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        googleMap = null
    }
}