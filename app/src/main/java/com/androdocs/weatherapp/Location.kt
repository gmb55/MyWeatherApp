package com.androdocs.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class Location (var context: Context, var activity: Activity) : AppCompatActivity(){


    val PERMISSION_ID = 42
    var mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)!!
    var value = "x"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


//    @SuppressLint("MissingPermission")
//    fun getLastLocation() : String {
//        var y = "y"
//
//        if (checkPermissions()) {
//                mFusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
//                    var location: Location? = task.result
//                    if (location == null) {
//                        requestNewLocationData()
//                    } else {
//                       var x = location.latitude.toString()
//                       var z = location.longitude.toString()
//
//                        y = "$x,$z"
//
//
//                    }
//                }
//        } else {
//            requestPermissions()
//        }
//        value = y
//     return   value
//    }

    fun getLastLocation(completion: (String) -> Unit) {
        mFusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
            var location: Location? = task.result
            if (location == null) {
                requestNewLocationData(completion)
            } else {
                completion("lat=" + location.latitude.toString() + "&lon=" + location.longitude.toString())                    }
        }
    }


    @SuppressLint("MissingPermission")
    private fun requestNewLocationData(completion: (String) -> Unit) {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest,object: LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    var location: Location = p0.lastLocation
                    completion("lat=" + location.latitude.toString() + "&lon=" + location.longitude.toString())
                }
            }, Looper.myLooper()
        )
    }

//    private val mLocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            var mLastLocation: Location = locationResult.lastLocation
//           mLastLocation.latitude.toString() + "," + mLastLocation.longitude.toString()
//        }
//    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        if (requestCode == PERMISSION_ID) {
//            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                getLastLocation()
//            }
//        }
//    }
}


