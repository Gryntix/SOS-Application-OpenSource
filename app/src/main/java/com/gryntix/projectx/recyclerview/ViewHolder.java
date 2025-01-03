package com.gryntix.projectx.recyclerview;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gryntix.projectx.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView name, phone, description;
    FrameLayout frameLayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        phone = itemView.findViewById(R.id.phone);
        description = itemView.findViewById(R.id.description);
        frameLayout = itemView.findViewById(R.id.framelayout);
    }
}
