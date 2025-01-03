package com.gryntix.projectx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class addcontact extends AppCompatActivity {

    EditText conNumber;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addcontact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        name = findViewById(R.id.editTextName);
        conNumber = findViewById(R.id.editTextPhoneNumber);

    }

    public void addContact(View view) {
        if(!name.getText().toString().trim().isEmpty() && !conNumber.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, name.getText().toString() + "  Saved to Close Contacts Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, activity_contacts.class);
            intent.putExtra("userName", String.valueOf(name));
            intent.putExtra("Number", String.valueOf(conNumber));
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Please Fill the Fields to Continue", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {@Override
            public void run() {
                runOnUiThread(() -> {
                    // Code to be executed after the delay (on the main thread)
                    Toast.makeText(addcontact.this, "5 SECONDS TEST", Toast.LENGTH_SHORT).show();
                });
            }
            }, 5000); // 5000 milliseconds = 5 seconds

        }
    }
}