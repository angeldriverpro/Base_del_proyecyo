package com.example.prueba_map

import android.Manifest
import android.app.Activity
import android.app.Instrumentation
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.huawei.hms.maps.*
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.PointOfInterest
import com.huawei.hms.push.ups.entity.UPSRegisterCallBack
import com.huawei.hms.site.api.SearchResultListener
import com.huawei.hms.site.api.SearchServiceFactory
import com.huawei.hms.site.api.model.DetailSearchRequest
import com.huawei.hms.site.api.model.DetailSearchResponse
import com.huawei.hms.site.api.model.SearchStatus
import com.huawei.hms.site.api.model.Site
import java.net.URLEncoder


class home_cliente : AppCompatActivity(), HuaweiMap.OnPoiClickListener {

    private lateinit var mapView: MapView
    private var huaweiMap: HuaweiMap? = null
    private val locationPermissionContract = registerForActivityResult(ActivityResultContracts.RequestPermission()){granted->onPermissionResult(granted)}
    private var locationService:LocationService? = null
    var clientesDBHelper: miSQLineHelper = miSQLineHelper(this)
    private lateinit var correo : String

    private fun onPermissionResult(granted: Boolean?) {
        if(granted == true)setupLocation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_cliente)

        /*intent.extras?.getString("correo").let {
            correo = it.toString()
            //traerNombre()
        }*/




        mapView = findViewById(R.id.mapView)
        mapView.onCreate(null)
        mapView.getMapAsync{map->onMapReady(map)}
        val locationButton = findViewById<FloatingActionButton>(R.id.locationBtn)
        locationButton.setOnClickListener{
            //prepareLocationRequest()


        }

    }

    /*private fun traerNombre() {
        val db: SQLiteDatabase = clientesDBHelper.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM cliente",null)

        if (cursor.moveToFirst()){
            do{
                if(correo == cursor.getString(2)){
                    Toast.makeText(this, "Bienvenido ${cursor.getString(1)}", Toast.LENGTH_SHORT).show()
                }
            }while (cursor.moveToNext())
        }
    }*/
  /*-------------------------------PERMISOS -------------------------------------*/
    

    /*-----------------------------PERMISOS----------------------------------*/

    private fun prepareLocationRequest() {
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) setupLocation()
        else locationPermissionContract.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

    }

    private fun setupLocation() {
           if (locationService == null){
               val locationService = LocationService(this)
               //Toast.makeText(this, "${locationService}", Toast.LENGTH_SHORT).show()
               locationService.startRequests()
           }

    }


    private fun onMapReady(map: HuaweiMap?) {
            this.huaweiMap = map
        huaweiMap?.apply {
            setOnPoiClickListener(this@home_cliente)
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(19.0,-99.0),15f)
            this.animateCamera(cameraUpdate)
        }


    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()

        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    /*CLICK DE LOS PUNTOS */
    override fun onPoiClick(poi: PointOfInterest?) {
        val resultListener: SearchResultListener<DetailSearchResponse> = object : SearchResultListener<DetailSearchResponse> {
            // Return search results upon a successful search.
            override fun onSearchResult(result: DetailSearchResponse?) {
                var site: Site? = null
                if (result == null || result.getSite().also { site = it } == null) {
                    return
                }
                Log.i("TAG", "siteId: ${site?.getSiteId()}, name: ${site?.getName()}")
                site?.let { displayInformation(it) }
            }
            // Return the result code and description upon a search exception.
            override fun onSearchError(status: SearchStatus) {
                Log.e("TAG", "Error : ${status.getErrorCode()}  ${status.getErrorMessage()}")
            }
        }


        poi?.let {
            val apikey = URLEncoder.encode("CwEAAAAA+Mo/x7tM85HN9UA36eYdMY/iPnff7UHPgeajv5L2vKinhaMkRsbQ/GtaWjwp4K3/kvlgNyGD2huPItwLo0fEqudKZZI=","UTF-8")
            val searchService = SearchServiceFactory.create(this,apikey)
            val request = DetailSearchRequest().apply {
                siteId = it.placeId
            }
            searchService.detailSearch(request,resultListener)
        }
    }
    private fun displayInformation(site:Site){
        AlertDialog.Builder(this)
            .setTitle(site.name)
            .setMessage(site.formatAddress)
            .setPositiveButton("ok") { dialog, _ -> dialog?.dismiss() }
            .setCancelable(false)
            .create().show()
    }



}
