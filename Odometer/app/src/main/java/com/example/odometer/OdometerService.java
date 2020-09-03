package com.example.odometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.Random;

public class OdometerService extends Service {
    public OdometerService() {
    }
    private final Random random = new Random();
    private LocationManager locManager;
    public static final String PERMISSION_STRING = android.Manifest.permission.ACCESS_FINE_LOCATION;

    private static double distanceInMeters;
    private static Location lastLocation = null;

    private final IBinder binder = new OdometerBinder();
    public class OdometerBinder extends Binder {
        OdometerService getOdometer() {
            return OdometerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double getDistance() {
        return  this.distanceInMeters;
    }



    private LocationListener listener;
    @Override
    public void onCreate() {
        super.onCreate();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distanceInMeters += location.distanceTo(lastLocation);
                lastLocation = location;
                Log.v("locaT", "l "+location);
            }
            @Override
            public void onProviderDisabled(String arg0) {}
            @Override
            public void onProviderEnabled(String arg0) {}
            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle bundle) {}
        };

        locManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED) {
            String provider = locManager.getBestProvider(new Criteria(), true);
            if (provider != null) {
                Log.v("checkPer", "ok");
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
            }
        }
//        Log.v("checkPer", "bad");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locManager != null && listener != null) {
            if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED) {
                locManager.removeUpdates(listener);
            }
            locManager = null;
            listener = null;
        }
    }
}
