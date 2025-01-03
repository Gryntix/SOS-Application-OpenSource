package com.gryntix.projectx.recyclerview;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;
import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.gryntix.projectx.R;

import java.util.List;

public class mAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<item> items;

    public mAdapter(Context context, List<item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.name.setText(items.get(position).getName());
        holder.description.setText(items.get(position).getDescription());
        holder.phone.setText(items.get(position).getPhone());

        holder.frameLayout.setOnClickListener(v -> {
            Toast.makeText(context, holder.name.getText(), Toast.LENGTH_SHORT).show();
        });
        holder.phone.setOnClickListener(v -> {
            Toast.makeText(context, "Phone number: " + holder.phone.getText(), Toast.LENGTH_SHORT).show();
        });
        holder.name.setOnClickListener(v -> {
            Toast.makeText(context, holder.name.getText(), Toast.LENGTH_SHORT).show();
        });
        holder.description.setOnClickListener(v -> {
            Toast.makeText(context, "Working on it...", Toast.LENGTH_SHORT).show();
        });
        holder.imageView.setOnClickListener(v -> {
            Snackbar.make(v,  "Number copied to Clipboard" , Snackbar.LENGTH_LONG).show();
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", holder.phone.getText());
            clipboard.setPrimaryClip(clip);

        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
