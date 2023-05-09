package com.example.foodapp.ui.game;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodapp.MainActivity;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentGoogleMapBinding;

public class GoogleMapFragment extends Fragment {
    private Button btnGrant;
    private FragmentGoogleMapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GoogleMapViewModel googleMapViewModel =
                new ViewModelProvider(this).get(GoogleMapViewModel.class);

        binding = FragmentGoogleMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        btnGrant = root.findViewById(R.id.btn_grant);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}