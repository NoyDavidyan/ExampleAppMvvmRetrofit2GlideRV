package com.example.fruitapp.fruits_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitapp.R;
import com.example.fruitapp.model.FruitModel;
import com.example.fruitapp.viewmodel.FruitViewModel;

import java.util.List;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class ListFragment extends Fragment implements  FruitListAdapter.ItemClickListener {

    private List<FruitModel> fruitModelList;
    private FruitViewModel viewModel;

    private FruitListAdapter adapter;

    onFruitClicked callBack;

    public interface onFruitClicked{
        void displaySelectedFruitDetails(FruitModel fruitModel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callBack = (onFruitClicked) context;

        }catch (ClassCastException ex){
            throw new ClassCastException("The activity must to implement onFruitClicked interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fruits_list_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fruits_recycler);
        final TextView noResultFoundTv = view.findViewById(R.id.noResultTv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter =  new FruitListAdapter(view.getContext(), fruitModelList, this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FruitViewModel.class);

        viewModel.getFruitsListObserver().observe(getViewLifecycleOwner(), new Observer<List<FruitModel>>() {
            @Override
            public void onChanged(List<FruitModel> fruitModels) {

                if(fruitModels != null) {
                    fruitModelList = fruitModels;
                    adapter.setFruitList(fruitModels);
                    noResultFoundTv.setVisibility(View.GONE);
                } else
                    noResultFoundTv.setVisibility(View.VISIBLE);
            }
        });
        viewModel.makeApiCall();

        return view;
    }

    @Override
    public void onFruitClicked(FruitModel fruitModel) {
        callBack.displaySelectedFruitDetails(fruitModel);
    }
}
