package com.example.minyawy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Places_Adapter extends RecyclerView.Adapter<Places_Adapter.viwHolder> {
    List<ItemPlaceModel> placeitem;

    public Places_Adapter(List<ItemPlaceModel> placeitem) {
        this.placeitem = placeitem;
    }

    @NonNull
    @Override
    public viwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_list,parent,false);
        viwHolder viwHolder=new viwHolder(view);
        return viwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viwHolder holder, int position) {
        holder.placeName.setText(placeitem.get(position).getPlaceName());
        holder.placedesc.setText(placeitem.get(position).getPlaceDes());
        holder.placephoto.setImageResource(placeitem.get(position).getItemPlacePhoto());

    }

    @Override
    public int getItemCount() {
        return placeitem.size();
    }

    public class viwHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placedesc;
        ImageView placephoto;

        public viwHolder(@NonNull View itemView) {
            super(itemView);
            placeName=(TextView)itemView.findViewById(R.id.text_placeName);
            placedesc=(TextView)itemView.findViewById(R.id.text_placedescrip);
            placephoto=(ImageView)itemView.findViewById(R.id.itemImage);

        }
    }
}
