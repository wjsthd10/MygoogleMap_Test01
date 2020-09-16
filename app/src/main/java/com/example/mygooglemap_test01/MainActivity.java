package com.example.mygooglemap_test01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        final SupportMapFragment mapFragment=(SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap=googleMap;

                LatLng seoul=new LatLng(37.56, 126.97);

                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(seoul);
                markerOptions.title("서울");
                markerOptions.snippet("대한민국의 수도");

                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoul,15.0f));

                LatLng mrhi=new LatLng(37.5608, 127.0346);
                MarkerOptions markerOptions1=new MarkerOptions();
                markerOptions1.position(mrhi);
                markerOptions1.title("미래능력개발교육원");
                markerOptions1.snippet("http://www.mrhi.or.kr");
                markerOptions1.anchor(0.5f, 1.0f);

                Marker marker=mMap.addMarker(markerOptions1);
                marker.showInfoWindow();

                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String title=marker.getTitle();
                        if (title.equals("미래능력개발교육원")){
                            Intent intent=new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            Uri uri=Uri.parse("http://www.mrhi.or.kr");
                            intent.setData(uri);

                            startActivity(intent);
                        }else if(title.equals("서울")){
                            Toast.makeText(MainActivity.this, "서울", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mrhi, 18f));

                UiSettings settings=mMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

            }
        });

    }
}