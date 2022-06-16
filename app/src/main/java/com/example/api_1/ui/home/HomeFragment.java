package com.example.api_1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.api_1.CatFact;
import com.example.api_1.ConsumeFact;

import com.example.api_1.databinding.FragmentHomeBinding;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView titulo = binding.titulo;
        final EditText inputTxt = binding.inputTxt;
        final Button submitButton = binding.submit;



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), inputTxt.getText().toString(), Toast.LENGTH_SHORT).show();

                Retrofit peticion = new Retrofit.Builder()
                        .baseUrl("https://catfact.ninja/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ConsumeFact consumeFact = peticion.create(ConsumeFact.class);

                Call<CatFact> call = consumeFact.getFacts();

                call.enqueue(new Callback<CatFact>() {
                    @Override
                    public void onResponse(Call<CatFact> call, Response<CatFact> response) {
                        if(!response.isSuccessful()){
                            titulo.setText("Er: " + response.code());
                            return;
                        }

                        CatFact catFacts = response.body();
                        titulo.setText("Fact: " + catFacts.getFact() + "\n" + "Length: " + catFacts.getLength());
                    }

                    @Override
                    public void onFailure(Call<CatFact> call, Throwable t) {
                        titulo.setText("Excep " + t.getMessage());
                    }
                });

            }
        });





        /*contentMainBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hello world!", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*cusButton = FragmentHomeBinding.inflate(inflater,container,false);
        Button newButton = cusButton.getRoot().findViewById();*/

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}