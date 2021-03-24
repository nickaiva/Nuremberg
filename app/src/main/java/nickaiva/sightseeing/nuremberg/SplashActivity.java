package nickaiva.sightseeing.nuremberg;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class SplashActivity extends AppCompatActivity
                            implements  GoogleApiClient.ConnectionCallbacks,
                                        GoogleApiClient.OnConnectionFailedListener{

    //private static final String DEBUG_TAG = "tag";    //remove before deployment
    private GoogleApiClient mGoogleApiClient;

    private static int SPLASH_TIME_OUT = 3000;      // Delay of 3 Seconds

    private static double mLatitude = 0;
    private static double mLongitude = 0;

    private GestureDetector detector;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();
            //Log.d("DEBUG_TAG", "Last known location " + mLatitude + " " + mLongitude);
            setmLatitude( mLastLocation.getLatitude());
            setmLongitude( mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
       // Log.d(DEBUG_TAG, "Location services suspended. Attempt to reconnect.");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        //Log.d(DEBUG_TAG, "Location services failed.ConnectionResult.getErrorCode() = " + result.getErrorCode());

    }


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.splash_image);
        if (imageView != null) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//CENTER_INSIDE
        }
        // Getting status
        int  status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status == ConnectionResult.SUCCESS)
           Log.d("DEBUG_TAG","Google Play Services are available");  //success message
        else  {
            //Log.d("DEBUG_TAG","Google Play Services are not available");  // not success
            int requestCode = 10;
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,status,requestCode);
            dialog.show();   //this will prompt to install
        }

        /* determine user location  http://blog.teamtreehouse.com/beginners-guide-location-android*/
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this,  nickaiva.sightseeing.nuremberg.MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }




    public static double getmLongitude() {
        return mLongitude;
    }

    private static void setmLongitude(double mLongitude) {
        SplashActivity.mLongitude = mLongitude;
    }

    public static double getmLatitude() {
        return mLatitude;
    }

    private static void setmLatitude(double mLatitude) {
        SplashActivity.mLatitude = mLatitude;
    }
}
