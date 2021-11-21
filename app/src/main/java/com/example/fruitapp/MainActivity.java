package com.example.fruitapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fruitapp.fruit_details.FruitDetailsFragment;
import com.example.fruitapp.fruits_list.ListFragment;
import com.example.fruitapp.model.FruitModel;
import com.example.fruitapp.viewmodel.FruitViewModel;


public class MainActivity extends AppCompatActivity implements ListFragment.onFruitClicked, FruitDetailsFragment.OnBackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFruitsListFragment();
    }

    @Override
    public void displaySelectedFruitDetails(FruitModel fruitModel) {
        setDetailsFruitFragment();
        shareDateWithDetailsFruitFragment(fruitModel);
    }

    private void shareDateWithDetailsFruitFragment(FruitModel fruitModel) {
        FruitViewModel viewModel = new ViewModelProvider(this).get(FruitViewModel.class);
        viewModel.setFruitObserver(fruitModel);
    }

    private void setFruitsListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new ListFragment())
                .commit();
    }

    private void setDetailsFruitFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, new FruitDetailsFragment())
                .commit();
    }

    @Override
    public void onBackClicked() {
        getSupportFragmentManager().popBackStack();
    }
}