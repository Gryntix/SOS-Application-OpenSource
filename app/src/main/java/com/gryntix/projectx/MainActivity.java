package com.gryntix.projectx;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SEND_SMS = 1;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 1;
    private static final String CHANNEL_ID = "Emergency_Notifications";
    private static final String CHANNEL_NAME = "Gryntix_Emergency_Notifications";
    private static final String CHANNEL_DES = "Emergency_Notifications_Des";
    ImageButton btn;
    ImageButton button;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        requestNotificationPermission();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(CHANNEL_DES);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);

        // if (isFirstLaunch) {
        // SharedPreferences.Editor editor = prefs.edit();
        // editor.putBoolean("first_launch", false);
        // editor.apply();
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.MyAlertDialogTheme))
                .setTitle("Welcome to Emergency")
                .setMessage("Seconds matter between life and death situations. \n" +
                        "In case of a situation like this, Emergency makes it faster to look for the right Emergency Responders and call them ASAP. \n\n" +
                        "Click on Learn to be ready for any emergency situations.")
                .setPositiveButton("Learn", (dialog, which) -> {
                    Intent intent = new Intent(this, VideoActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "Lets Learn a Little!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .setIcon(R.drawable.baseline_emergency)
                .show();
        //  }
        btn = findViewById(R.id.location_btn);
        button = findViewById(R.id.btn_call);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        btn.setOnClickListener(view -> getLocation());
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            String message = "My Location - Latitude: " + latitude + ", Longitude: " + longitude;
                            String mapsUrl = "https://maps.google.com/?q=" + latitude + "," + longitude;
                            Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl));
                            startActivity(mapsIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "Unable to get location.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted
            Log.d("PERMISSION", "Notification permission already granted");
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            Log.d("PERMISSION", "Should show rationale");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_REQUEST_CODE);
        } else {
            // No explanation needed; request the permission
            Log.d("PERMISSION", "Requesting permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_REQUEST_CODE);
        }
    }


    public void Call(View view) {
        Intent myIntent = new Intent(this, Activity_Shake.class);
        startActivity(myIntent);

    }

    public void close(View view) {
        Intent close = new Intent(this, activity_contacts.class);
        startActivity(close);
        Toast.makeText(this, "Close Contacts", Toast.LENGTH_SHORT).show();
    }

    private void openMapAtLocation(double latitude, double longitude) {
        String mapsUrl = "https://maps.google.com/?q=" + latitude + "," + longitude;
        Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl));
        startActivity(mapsIntent);
    }

    private void displayNotification() {

        Intent intent = new Intent(this, Activity_Shake.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.baseline_emergency)
                        .setContentTitle("Quick Access to Emergency")
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.baseline_crisis_alert_24, "SOS", pendingIntent)
                        .setOngoing(true)
                        .setAutoCancel(false)
                        .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        notificationManagerCompat.notify(0, mBuilder.build());
    }


    public void displayNtf(View view) {
        displayNotification();
    }


    public void sms(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_CODE_SEND_SMS);
        } else {
            sendSms();
            Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show();
        }
    }


    private void sendSms() {
        String phoneNumber = "0535200945";
        String message = "Test SMS message Gryntix";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);

    }
}