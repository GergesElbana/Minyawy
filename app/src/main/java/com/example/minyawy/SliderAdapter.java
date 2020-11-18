package com.example.minyawy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.database.core.Context;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SliderAdapter extends PagerAdapter {
    private int imageSlider[] ={R.drawable.download,R.drawable.download};
     private Context  context;
     private LayoutInflater layoutInflater;

     SliderAdapter(Context context){

         this.context=context;
     }

    @Override
    public int getCount() {
        return imageSlider.length ;
    }


      @NonNull


   @Override
    public Object instantiateItem( ViewGroup container, int position) {
    //  layoutInflater =  (LayoutInflater) context.getSystemService (context.LAYOUT_INFLATER_SERVICE);
    // layoutInflater = ((HomeActivity) context).getLayoutInflater();

        View item_view=layoutInflater.inflate(R.layout.slider_layout,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.sliderimage);
        imageView.setImageResource(imageSlider[position]);
        container.addView(item_view);


        return item_view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((LinearLayout)object );
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }
}
