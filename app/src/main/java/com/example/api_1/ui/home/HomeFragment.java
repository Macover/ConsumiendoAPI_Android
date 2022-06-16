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

import com.example.api_1.databinding.ContentMainBinding;
import com.example.api_1.databinding.FragmentHomeBinding;

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
                titulo.setText(inputTxt.getText().toString());
                Toast.makeText(getContext(), inputTxt.getText().toString(), Toast.LENGTH_SHORT).show();
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