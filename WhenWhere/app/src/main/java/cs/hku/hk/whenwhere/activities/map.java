package cs.hku.hk.whenwhere.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import cs.hku.hk.whenwhere.R;
public class map extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    private GoogleMap map;
    private Marker marker_taroko;
    private Marker marker_yushan;
    private Marker marker_kenting;
    private Marker marker_yangmingshan;
    private TextView tvMarkerDrag;
    private LatLng taroko;
    private LatLng yushan;
    private LatLng kenting;
    private LatLng yangmingshan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initPoints();
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fmMap);
        mapFragment.getMapAsync(this);
      //  tvMarkerDrag = (TextView) findViewById(R.id.tvMarkerDrag);
    }
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        setUpMap();
        map.setOnMarkerClickListener(this);
    }
    private void initPoints() {
        taroko = new LatLng(24.151287, 121.625537);
        yushan = new LatLng(23.791952, 120.861379);
        kenting = new LatLng(21.985712, 120.813217);
        yangmingshan = new LatLng(25.091075, 121.559834);
    }
    private void setUpMap() {
        //mMap=map;
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }
        map.getUiSettings().setZoomControlsEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(taroko)
                .zoom(7)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);

        addMarkersToMap();
       // map.setOnMarkerClickListener(this);
       // map.setInfoWindowAdapter(new MyInfoWindowAdapter());
/*
        MyMarkerListener myMarkerListener = new MyMarkerListener();
        map.setOnMarkerClickListener(myMarkerListener);
        map.setOnInfoWindowClickListener(myMarkerListener);
        map.setOnMarkerDragListener(myMarkerListener);
        */
    }
    private void addMarkersToMap() {
        //从database中取出已经添加的marker
        marker_taroko = map.addMarker(new MarkerOptions()
                .position(taroko)
               // .title(getString(R.string.marker_title_taroko))
                .title("taroko")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
        marker_taroko.setTag(0);
        marker_yushan = map.addMarker(new MarkerOptions().position(yushan)
                .title(getString(R.string.marker_title_yushan))
                .draggable(true));

        marker_kenting = map.addMarker(new MarkerOptions().position(kenting)
                .title(getString(R.string.marker_title_kenting))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        marker_yangmingshan = map.addMarker(new MarkerOptions()
                .position(yangmingshan)
                .title(getString(R.string.marker_title_yangmingshan))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }
    public void onClick(View view){
        EditText inputtext=findViewById(R.id.search_address);
        String address =inputtext.getText().toString();
        List<Address> addressList=null;
        if(address !=null ||!address.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList=geocoder.getFromLocationName(address,6);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address location=addressList.get(0);
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title(address)).setTag(5);
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            Toast.makeText(getApplicationContext(),location.getLatitude()+" "+location.getLongitude(),Toast.LENGTH_LONG);

        }
    }
    /*
    private class MyMarkerListener implements GoogleMap.OnMarkerClickListener,
            GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener {
        @Override
        public boolean onMarkerClick(Marker marker) {
            showToast(marker.getTitle());
            return false;
        }

        @Override
        public void onInfoWindowClick(Marker marker) {
            showToast(marker.getTitle());
        }

        @Override
        public void onMarkerDragStart(Marker marker) {
            String text = "onMarkerDragStart";
            tvMarkerDrag.setText(text);
        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            String text = "onMarkerDragEnd";
            tvMarkerDrag.setText(text);
        }

        @Override
        public void onMarkerDrag(Marker marker) {
            String text = "onMarkerDrag.  Current Position: "
                    + marker.getPosition();
            tvMarkerDrag.setText(text);
        }
    }
    private class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View infoWindow;

        MyInfoWindowAdapter() {
            infoWindow = View.inflate(map.this, R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            int logoId;
            if (marker.equals(marker_yangmingshan)) {
                logoId = R.drawable.logo_yangmingshan;
            } else if (marker.equals(marker_taroko)) {
                logoId = R.drawable.logo_taroko;
            } else if (marker.equals(marker_yushan)) {
                logoId = R.drawable.logo_yushan;
            } else if (marker.equals(marker_kenting)) {
                logoId = R.drawable.logo_kenting;
            } else {
                logoId = 0;
            }

            ImageView ivLogo = ((ImageView) infoWindow
                    .findViewById(R.id.ivLogo));
            ivLogo.setImageResource(logoId);

            String title = marker.getTitle();
            TextView tvTitle = ((TextView) infoWindow
                    .findViewById(R.id.tvTitle));
            tvTitle.setText(title);

            String snippet = marker.getSnippet();
            TextView tvSnippet = ((TextView) infoWindow
                    .findViewById(R.id.tvSnippet));
            tvSnippet.setText(snippet);
            return infoWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }
    public void onClearMapClick(View view) {
        map.clear();
    }

    public void onResetMapClick(View view) {
        map.clear();
        addMarkersToMap();
    }
*/
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
            alertdialogbuilder.setMessage("Would you like to add "+marker.getTitle()+" to your activity place list?");
            alertdialogbuilder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(map.this, "username:"+ marker.getTitle(),Toast.LENGTH_SHORT).show();
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
                }});
            alertdialogbuilder.setNegativeButton("Cancel", click2);
            AlertDialog alertdialog1=alertdialogbuilder.create();
            alertdialog1.show();

        }
        return false;
    }
    private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface arg0,int arg1)
        {
            arg0.cancel();
        }
    };

}
