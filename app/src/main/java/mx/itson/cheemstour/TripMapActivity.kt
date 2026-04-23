package mx.itson.cheemstour

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import mx.itson.cheemstour.entities.Trip
import mx.itson.cheemstour.utis.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripMapActivity : AppCompatActivity(), OnMapReadyCallback {

    var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trip_map)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var mapFragment = supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getTrips()

    }

    fun getTrips(){
        val call: Call<List<Trip>> = RetrofitUtil().getApi().getTrips()

        call.enqueue(object : Callback<List<Trip>> {

            override fun onResponse(call: Call<List<Trip>?>, response: Response<List<Trip>>) {
                val trips : List<Trip> = response.body()!!
                for(t in trips) {
                }

            }

            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                Log.e("Error calling API", "Error: ${t.message}")
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try {
            map = googleMap
            map.mapType =
        }catch (ex: Exception){
            Log.e("Error loading map", ex.toString())
        }
    }
}