package com.example.uts_pam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_pam.ListPhotoActivity;
import com.example.uts_pam.Model.Photos;
import com.example.uts_pam.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListPhoto extends RecyclerView.Adapter<AdapterListPhoto.ViewHolder> {

    Context context;
    List<Photos> viewItems;

    public AdapterListPhoto(Context listPhotoActivity, List<Photos> viewItems) {
        this.context = context;
        this.viewItems = viewItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_photos_layout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String thumbnailUrl = viewItems.get(position).getThumbnailUrl();
        Picasso.with(context).load(thumbnailUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.icon);

        String id = viewItems.get(position).getId().toString();
        holder.txtId.setText(id);

        holder.txtName.setText(viewItems.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        ImageView icon;
        TextView txtId, txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            txtId   = itemView.findViewById(R.id.txtId);
            txtId   = itemView.findViewById(R.id.txtName);
        }
    }
}
