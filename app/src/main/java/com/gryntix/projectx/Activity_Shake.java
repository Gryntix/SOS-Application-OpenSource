package com.gryntix.projectx;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Shake extends AppCompatActivity {

    private ShakeDetector shakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shake);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(() -> {
                Toast.makeText(Activity_Shake.this, "Shake detected!", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.MyAlertDialogTheme))
                        .setTitle("Choose an option")
                        .setMessage("Select one of the following first responders:")
                        .setPositiveButton("כבאות והצלה", (dialog, which) -> {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:102"));
                            startActivity(intent);
                            Toast.makeText(this, "102", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("מדא", (dialog, which) -> {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:101"));
                            startActivity(intent);
                            Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
                        })
                        .setNeutralButton("משטרה", (dialog, which) -> {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:100"));
                            startActivity(intent);
                            Toast.makeText(this, "100", Toast.LENGTH_SHORT).show();
                        })
                        .show();
        });


        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    public void showList(View view) {
        Intent intent = new Intent(this, activity_list.class);
        startActivity(intent);
    }

    public void Callexecute(View view) {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.MyAlertDialogTheme))
                .setTitle("Choose an option")
                .setMessage("Select one of the following first responders:")
                .setPositiveButton("כבאות והצלה", (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:102"));
                    startActivity(intent);
                    Toast.makeText(this, "102", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("מדא", (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:101"));
                    startActivity(intent);
                    Toast.makeText(this, "101", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("משטרה", (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:100"));
                    startActivity(intent);
                    Toast.makeText(this, "100", Toast.LENGTH_SHORT).show();
                })
                .show();
    }
}