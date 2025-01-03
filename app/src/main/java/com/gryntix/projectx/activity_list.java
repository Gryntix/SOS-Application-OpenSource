package com.gryntix.projectx;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gryntix.projectx.recyclerview.item;
import com.gryntix.projectx.recyclerview.mAdapter;

import java.util.ArrayList;
import java.util.List;

public class activity_list extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<item> items = new ArrayList<>();
        items.add(new item("testName", "1", "007654567", R.drawable.img_phone));
        items.add(new item("מגן דוד אדום", "עזרה ראשונה", "101", R.drawable.ems));
        items.add(new item("משטרה", "משטרת ישראל", "100", R.drawable.police));
        items.add(new item("מכבי אש", "כבאות והצלה", "102", R.drawable.firefighters));
        items.add(new item("1", "1", "007654567", R.drawable.img_phone));
        items.add(new item("1", "1", "007654567", R.drawable.img_phone));

        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new mAdapter(getApplicationContext(), items));


    }

}