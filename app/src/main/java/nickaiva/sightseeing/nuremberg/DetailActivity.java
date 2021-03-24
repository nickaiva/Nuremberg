package nickaiva.sightseeing.nuremberg;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class DetailActivity extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks,
                                                        GoogleApiClient.OnConnectionFailedListener {

    //private static final String DEBUG_TAG = "tag";// remove before deployment

    private GoogleApiClient mGoogleApiClient;

    private static double mLatitude = SplashActivity.getmLatitude();
    private static double mLongitude = SplashActivity.getmLongitude();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

         ImageView detailImage;
        //View.OnTouchListener listener=null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailImage = (ImageView) findViewById(R.id.detail_image);
        //detailImage.setOnTouchListener(listener);
        TextView detailName = (TextView) findViewById(R.id.detail_name);
        TextView detailDistance = (TextView) findViewById(R.id.detail_distance);
        TextView detailText = (TextView) findViewById(R.id.detail_text);
        detailText.setMovementMethod(new ScrollingMovementMethod());

        ImageView detailWebLink = (ImageView) findViewById(R.id.detail_web_link);
        ImageView detailDirLink = (ImageView) findViewById(R.id.detail_dir_link);

        int i = MainActivity.currentItem;



        /* determine user location  http://blog.teamtreehouse.com/beginners-guide-location-android*/
            // Create an instance of GoogleAPIClient.

            if (mGoogleApiClient == null) {
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();
            }


        /* calculation of distance to target location */
        Log.d("DEBUG_TAG", "Target coordinates of: " + i + " latitude " + MainData.latitudes[i]  +
                " longitude  of i " + i + " " + MainData.longitudes[i]);
        double distance = Distance.distance(getmLatitude(), getmLongitude(), MainData.latitudes[i], MainData.longitudes[i]);
        setTitle(getString(R.string.app_name) + " - " + getString(MainData.nameArray[i]));
        detailImage.setImageResource(MainData.detailImageArray[i]);
        detailName.setText(MainData.nameArray[i]);
        if ( mLatitude != 0 || mLongitude != 0)
            detailDistance.setText(getString(R.string.distance)+" " + String.valueOf(distance).substring(0,4)  + " Km");
        else detailDistance.setText(getString(R.string.distanceUnavailable));
        detailText.setText(MainData.detailTextArray[i]);
        detailWebLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(getString(MainData.detailWebLink[MainActivity.currentItem])));
                startActivity(intent);
            }
        });
        detailDirLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(MainData.detailDirLink[MainActivity.currentItem]));
                startActivity(intent);
            }
        });
        //display popup
        Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.popup_detail,
                Snackbar.LENGTH_SHORT)
                .show();
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
    public void onConnected(Bundle connectionHint) {
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
            Log.i("DEBUG_TAG", "Last known location " + mLatitude + " " + mLongitude);
            setmLatitude( mLastLocation.getLatitude());
            setmLongitude( mLastLocation.getLongitude());
        }

    }


    @Override
    public void onConnectionSuspended(int i) {
        //Log.d(DEBUG_TAG, "Location services suspended. Attempt to reconnect.");
        mGoogleApiClient.connect();
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
       // Log.d(DEBUG_TAG, "Location services failed.ConnectionResult.getErrorCode() = " + result.getErrorCode());

    }

    private void setmLatitude(double mLatitude) {
        DetailActivity.mLatitude = mLatitude;
    }

    private void setmLongitude(double mLongitude) {
        DetailActivity.mLongitude = mLongitude;
    }

    private double getmLatitude() {
        return mLatitude;
    }

    private double getmLongitude() {
        return mLongitude;
    }



}
