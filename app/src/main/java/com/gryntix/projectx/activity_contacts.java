package com.gryntix.projectx;

import static kotlinx.coroutines.DelayKt.delay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class activity_contacts extends AppCompatActivity {

    String receivedName;
    String recievedNumber;
    int amountCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        receivedName = getIntent().getStringExtra("userName");
        recievedNumber = getIntent().getStringExtra("Number");
    }
    public void OnClick(View view) {

        Toast.makeText(this, "test" + recievedNumber, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + recievedNumber));
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
        finish();
    }
    public void OnClick2(View view) {
        Intent intent = new Intent(this, addcontact.class);
        startActivity(intent);
        finish();
    }
}