package com.geektech.hw1_4m.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.geektech.hw1_4m.R;
import com.geektech.hw1_4m.databinding.FragmentHomeBinding;
import com.geektech.hw1_4m.ui.home.Adapter.TaskAdapter;
import com.geektech.hw1_4m.ui.home.Model.TaskModel;


public class HomeFragment extends Fragment {

    private final TaskAdapter adapter = new TaskAdapter();
    private FragmentHomeBinding binding;
    private NavHostController controller;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        toDetailFragment();
        onFragmentResult();
        initController();
        actionBar();
    }

    private void actionBar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    private void initAdapter() {
        binding.item.setAdapter(adapter);
    }


    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostController != null;
        controller = (NavHostController) navHostController.getNavController();
    }

    private void onFragmentResult() {
        requireActivity().getSupportFragmentManager().setFragmentResultListener("Work",
                this, (requestKey, result) -> {
                    if (requestKey.equals("Work")) {
                        String title = result.getString("title");
                        String date = result.getString("date");
                        adapter.addNewNote(new TaskModel(title, "создано в; " + date));
                        Toast.makeText(requireContext(), "Успешно сохранено!!!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void toDetailFragment() {
        binding.btnButton.setOnClickListener(v -> controller.navigate(R.id.detailFragment));
    }


}