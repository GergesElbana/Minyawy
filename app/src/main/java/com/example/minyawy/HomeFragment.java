package com.example.minyawy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tool_Bar)
    Toolbar toolBar;
 //   @BindView(R.id.Slider)
 //   ImageSlider Slider;
   // @BindView(R.id.PlaceRecycler)
 //   RecyclerView PlaceRecycler;
    ArrayList<Place_Model> place;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView  PlaceRecycler= (RecyclerView) v.findViewById(R.id.PlaceRecycler) ;
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider, null));
        slideModels.add(new SlideModel(R.drawable.slider, null));
        ImageSlider Slider=(ImageSlider)v.findViewById(R.id.Slider);
        Slider.setImageList(slideModels, true);

        ArrayList<Place_Model> place = new ArrayList<>();
        place.add(new Place_Model("Cafe", R.drawable.cafe));
        place.add(new Place_Model("Pharmacy", R.drawable.pharmacy));
        place.add(new Place_Model("Restaurant", R.drawable.restaurant));
        place.add(new Place_Model("bank", R.drawable.bank));
        place.add(new Place_Model("clothes", R.drawable.clothes));
        place.add(new Place_Model("hotel", R.drawable.hotel));
        place.add(new Place_Model("hospital", R.drawable.hospital));
        place.add(new Place_Model("Men_Suit", R.drawable.men_suit));
        place.add(new Place_Model("souvenirs", R.drawable.sovener));
        place.add(new Place_Model("bread", R.drawable.bread));
        RecyclerAdapter adapter = new RecyclerAdapter(place,getActivity());
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        PlaceRecycler.setLayoutManager(layoutManager);
        PlaceRecycler.setItemAnimator(new DefaultItemAnimator());
        PlaceRecycler.setAdapter(adapter);

        return v;
    }
}