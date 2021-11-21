package com.example.fruitapp.fruit_details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.fruitapp.R;
import com.example.fruitapp.model.FruitModel;
import com.example.fruitapp.viewmodel.FruitViewModel;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class FruitDetailsFragment extends Fragment {

    ImageButton back_ib;
    ImageView imageIv;
    TextView nameTv;
    TextView descriptionTv;
    TextView priceTv;

    OnBackListener callBack;

    public interface OnBackListener {
        void onBackClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callBack = (OnBackListener) context;

        } catch (ClassCastException ex) {
            throw new ClassCastException("The activity must to implement OnBackListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fruit_details_fragment, container, false);

        back_ib = view.findViewById(R.id.back_ib);
        imageIv = view.findViewById(R.id.image_iv);
        nameTv = view.findViewById(R.id.name_tv);
        descriptionTv = view.findViewById(R.id.description_tv);
        priceTv = view.findViewById(R.id.price_tv);


        back_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onBackClicked();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FruitViewModel viewModel = new ViewModelProvider((requireActivity())).get(FruitViewModel.class);
        viewModel.getFruitObserver().observe(getViewLifecycleOwner(), new Observer<FruitModel>() {
            @Override
            public void onChanged(FruitModel fruitModel) {

                nameTv.setText(fruitModel.getNameStr());
                descriptionTv.setText(fruitModel.getDescriptionStr());
                priceTv.setText(fruitModel.getPriceInt() + "");

                if (fruitModel.getImageUrlStr() != null) {
                    Glide.with(getContext())
                            .load(fruitModel.getImageUrlStr())
                            .error(R.drawable.ic_no_image)
                            .circleCrop()
                            .into(imageIv);
                }

                /*An example second option to implement load imageView with a Picasso library.
                  Before uses should be uncomment Picasso libraries in a build.gradle file*/
/*                if (fruitModel.getImageUrlStr() != null) {
                    Picasso.get().load(fruitModel.getImageUrlStr()).resize(300, 300).transform(new CropCircleTransformation()).into(imageIv,
                            new Callback() {
                                @Override
                                public void onSuccess() { }

                                @Override
                                public void onError(Exception e) {
                                    Toast.makeText(getContext(), "Error - " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }*/
            }
        });
    }
}
