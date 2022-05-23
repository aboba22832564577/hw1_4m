package com.geektech.hw1_4m.ui.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.hw1_4m.R;
import com.geektech.hw1_4m.adapter.BoardAdapter;
import com.geektech.hw1_4m.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private NavHostController controller;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding = FragmentBoardBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        setHasOptionsMenu(true);
        initController();
        initAnimate();
        initClick();
        initDefault();
        initListener();
        new TabLayoutMediator(binding.tabLayout, binding.vpBoard, ((tab, position) ->
                tab.setIcon(R.drawable.tab_selector))).attach();
    }

    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostController != null;
        controller = (NavHostController) navHostController.getNavController();
    }

    private void initDefault() {
        binding.txtSkip.setTranslationX(-100);
        binding.txtFinish.setEnabled(false);
    }
    private void initAnimate() {
        binding.txtSkip.animate().translationX(0).setDuration(2000).start();
    }
    private void initClick() {
        binding.txtFinish.setOnClickListener(v -> controller.navigate(R.id.navigation_home));
        binding.txtSkip.setOnClickListener(v -> binding.vpBoard.setCurrentItem(3));
    }
    private void initListener() {
        binding.vpBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 3) {
                    binding.txtFinish.animate().alpha(1).setDuration(2000).start();
                    binding.txtFinish.setEnabled(true);
                    binding.txtSkip.animate().alpha(0).setDuration(1000).start();
                    binding.txtSkip.setEnabled(false);
                } else {
                    binding.txtSkip.animate().alpha(1).setDuration(2000).start();
                    binding.txtSkip.setEnabled(true);
                    binding.txtFinish.animate().alpha(0).setDuration(1000).start();
                    binding.txtFinish.setEnabled(false);
                }

            }
        });
    }
    private void initAdapter() {
        BoardAdapter adapter = new BoardAdapter();
        binding.vpBoard.setAdapter(adapter);
    }
}