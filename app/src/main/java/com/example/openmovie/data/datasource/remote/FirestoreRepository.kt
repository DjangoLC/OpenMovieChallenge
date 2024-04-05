package com.example.openmovie.data.datasource.remote

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.InputStream
import javax.inject.Singleton

data class Location(val latitude: Double, val longitude: Double) {
    constructor() : this(0.0, 0.0)
}

@Singleton
class FirestoreRepository {

    companion object {
        const val LOCATIONS = "locations"
    }

    private val db = Firebase.firestore

    fun saveLocation(location: Location) {
        db.collection(LOCATIONS)
            .add(location)
            .addOnCompleteListener {
                //handle completion
                Log.i("addOnCompleteListener", "was completed")
            }
            .addOnCanceledListener {
                //handle cancellation
                Log.i("addOnCanceledListener", "was canceled")
            }
            .addOnFailureListener {
                //handle failure
                Log.e("addOnFailureListener", it.toString())
            }
    }

    fun getLocations(callback: (Set<Location>) -> Unit) {
        //one time request to locations
        db.collection(LOCATIONS).get().addOnSuccessListener { query ->
            query?.let {
                try {
                    val docs = it.documents.mapNotNull { doc ->
                        doc.toObject<Location>()
                    }.toSet()
                    callback.invoke(docs)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            }
        }
    }

    fun uploadFile(reference: String, inputStream: InputStream, callback: (UploadFileState) -> Unit) {
        callback(UploadFileState.Uploading)
        Firebase.storage
            .getReference(reference)
            .putStream(inputStream)
            .addOnSuccessListener {
                Log.e("Firebase.storage", "success")
                callback(UploadFileState.Success)
            }
            .addOnFailureListener {
                Log.e("Firebase.storage", "failure while upload image")
                callback(UploadFileState.Error)
            }
    }

    sealed class UploadFileState {
        data object Uploading: UploadFileState()
        data object Success: UploadFileState()
        data object Error: UploadFileState()
    }

}