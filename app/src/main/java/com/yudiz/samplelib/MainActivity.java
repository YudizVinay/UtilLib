package com.yudiz.samplelib;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vinay.utillib.UtilLib;
import com.vinay.utillib.imagechooser.ChooseType;
import com.vinay.utillib.imagechooser.OnImageChooserListener;
import com.vinay.utillib.locationmanage.OnLocationPickListener;
import com.vinay.utillib.permissionutils.PermissionEverywhere;
import com.vinay.utillib.permissionutils.PermissionResponse;
import com.vinay.utillib.permissionutils.PermissionResultCallback;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv;
    private Button ivChoose, permissionCheck, getLocation;
    private TextView ivPath, permissionStatus, locationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageview);

        ivChoose = (Button) findViewById(R.id.imagechoose);
        permissionCheck = (Button) findViewById(R.id.permissioncheck);
        getLocation = (Button) findViewById(R.id.getlocation);

        ivPath = (TextView) findViewById(R.id.imagepath);
        permissionStatus = (TextView) findViewById(R.id.permissionstatus);
        locationInfo = (TextView) findViewById(R.id.location);

        ivChoose.setOnClickListener(this);
        permissionCheck.setOnClickListener(this);
        getLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagechoose:
                // dialog with camera and gallery
                UtilLib.getPhoto(this, ChooseType.REQUEST_ANY)
                        .enqueue(new OnImageChooserListener() {

                            @Override
                            public void onImageChoose(String path) {
                                ivPath.setText("" + path);
                                Glide.with(MainActivity.this).load(new File(path)).into(iv);
                            }
                        });
                break;
            case R.id.permissioncheck:
                PermissionEverywhere.getPermission(this, new String[]{Manifest.permission.CAMERA}, 12)
                        .enqueue(new PermissionResultCallback() {
                            @Override
                            public void onComplete(PermissionResponse permissionResponse) {
                                permissionStatus.setText(permissionResponse.isAllGranted() ? "Enable" : "Disable");
                            }
                        });
                break;
            case R.id.getlocation:
                UtilLib.getLocationManager(MainActivity.this).getLocation(new OnLocationPickListener() {
                    @Override
                    public void getLastLocation(Location location) {
                        locationInfo.setText("lng:" + location.getLongitude() + " lat:" + location.getLatitude());
                    }

                    @Override
                    public void onLocationChanged(Location location) {
                        locationInfo.setText("lng:" + location.getLongitude() + " lat:" + location.getLatitude());
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, "Location Error." + error, Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }
}