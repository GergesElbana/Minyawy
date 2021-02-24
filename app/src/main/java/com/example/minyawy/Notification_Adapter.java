package com.example.minyawy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.viewHolder> {

    private Context context;
    private List<FetchPlaceName> fetchPlaceNames;

    public Notification_Adapter(Context context, List<FetchPlaceName> fetchPlaceNames) {
        this.context = context;
        this.fetchPlaceNames = fetchPlaceNames;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        viewHolder viewHolder=new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {

        holder.n_placeName.getText().toString();
        holder.n_placeName.setText(fetchPlaceNames.get(position).getName());
        holder.n_placedesc.setText(fetchPlaceNames.get(position).getDescrip());
        Glide.with(context)
                .load(fetchPlaceNames.get(position)
                        .getPhoto()).into(holder.n_placephoto);

        holder.n_placeName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


              /* Intent ih=new Intent(activity,RestaurantDescription.class);
               activity.startActivity(ih);*/
                Intent intent=new Intent(context,RestaurantDescription.class);
                intent.putExtra("name",fetchPlaceNames.get(position).getName());
                intent.putExtra("dis",fetchPlaceNames.get(position).getDescrip());
                intent.putExtra("photo",fetchPlaceNames.get(position).getPhoto());
                intent.putExtra("num",fetchPlaceNames.get(position).getNumber());
                intent.putExtra("loc",fetchPlaceNames.get(position).getLocation());
                context.startActivity(intent);




            }
        });


    }

    @Override
    public int getItemCount() {
        return fetchPlaceNames.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView n_placeName, n_placedesc;
        ImageView n_placephoto;
        CardView n_card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            n_placeName=(TextView)itemView.findViewById(R.id.notif_text_placeName);
            n_placedesc =(TextView)itemView.findViewById(R.id.notif_text_placedescrip);
            n_placephoto=(ImageView)itemView.findViewById(R.id.notif_itemImage);
            n_card=itemView.findViewById(R.id.notif_card0);
        }
    }
}
