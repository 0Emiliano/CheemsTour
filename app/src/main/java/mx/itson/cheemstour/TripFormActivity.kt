package mx.itson.cheemstour

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class TripFormActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {

    var map: GoogleMap? = null
    lateinit var name: EditText
    lateinit var city: EditText
    lateinit var btnSave: Button
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trip_form)

        // Initialize views AFTER setContentView
        name = findViewById(R.id.txt_name)
        city = findViewById(R.id.txt_city)
        btnSave = findViewById(R.id.btn_save)
        btnSave.setOnClickListener(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_form) as SupportMapFragment
        mapFragment.getMapAsync(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(view: View?) {
        // Here you would call your API to save name, city, latitude, and longitude
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try {
            map = googleMap
            map?.mapType = GoogleMap.MAP_TYPE_HYBRID
            map?.clear()

            // Initial position for the marker
            val initialPos = LatLng(latitude, longitude)

            map?.addMarker(
                MarkerOptions()
                    .position(initialPos)
                    .title("Drag me!")
                    .draggable(true)
            )

            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPos, 8f))

            // Proper implementation of the Drag Listener
            map?.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragStart(marker: Marker) {
                    Log.d("Marker", "Started dragging")
                }

                override fun onMarkerDrag(marker: Marker) {
                    // This runs while the marker is moving
                }

                override fun onMarkerDragEnd(marker: Marker) {
                    // Update your variables when the user drops the marker
                    val position = marker.position
                    latitude = position.latitude
                    longitude = position.longitude

                    Log.d("Location", "New Lat: $latitude, New Lon: $longitude")
                }
            })

        } catch (ex: Exception) {
            Log.e("Error loading map", ex.toString())
        }
    }
}