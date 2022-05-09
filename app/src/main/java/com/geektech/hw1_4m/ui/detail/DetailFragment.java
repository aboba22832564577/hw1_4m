package com.geektech.hw1_4m.ui.detail;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.hw1_4m.databinding.FragmentDetailBinding;

import java.util.Date;
import java.util.Locale;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    String date = new SimpleDateFormat("dd.MM.yyyy   HH:mm", Locale.getDefault()).
            format(new Date());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSave();
    }


    private void initSave() {
        binding.btnSave.setOnClickListener(v -> {
            String title = binding.saveText.getText().toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("date", date);
            requireActivity().getSupportFragmentManager().setFragmentResult("Work", bundle);
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}