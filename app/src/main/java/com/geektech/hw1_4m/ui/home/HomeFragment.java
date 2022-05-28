package com.geektech.hw1_4m.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.geektech.hw1_4m.App;
import com.geektech.hw1_4m.R;
import com.geektech.hw1_4m.databinding.FragmentHomeBinding;
import com.geektech.hw1_4m.adapter.TaskAdapter;
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
        listenGetDate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.bottom_nav_menu2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_searchHome: {
                adapter.sortByAlphabet();
                return true;
            }
            case R.id.action_sortHome:{
                adapter.setByDate();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void initAdapter() {
        adapter.setLongClick((model, position) -> {
            App.dataBase.caseDao().deleteTask(model);
            initAdapter();
        });


        adapter.setIHomeClickListener((model, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", model.getTitle());
            bundle.putString("date", model.getCreated());
            bundle.putInt("position", position);
            controller.navigate(R.id.detailFragment, bundle);
        });
        binding.item.setAdapter(adapter);
        onResume();
    }




    @Override
    public void onResume() {
        super.onResume();
        adapter.addItem(App.dataBase.caseDao().getAllTasks());
    }

    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostController != null;
        controller = (NavHostController) navHostController.getNavController();
    }

    private void onFragmentResult() {
        requireActivity().getSupportFragmentManager().setFragmentResultListener(
                "newTask", this, (requestKey, result) -> {
            if (requestKey.equals("newTask")){
                String title = result.getString("title");
                String date = result.getString("date");
                App.dataBase.caseDao().addTask(new TaskModel(title,"Сделано в: " + date));
            }
        });
    }

    private void listenGetDate() {
        requireActivity().getSupportFragmentManager().setFragmentResultListener(
                "getTask", this, (requestKey, result) -> {
                    if (requestKey.equals("getTask")){
                        String title = result.getString("title");
                        String date = result.getString("date");
                        int position = result.getInt("position");
                        App.dataBase.caseDao().update(new TaskModel(position, title,
                                "Сделано в: " + date));
                    }
                });
    }


    private void toDetailFragment() {
        binding.btnButton.setOnClickListener(v -> controller.navigate(R.id.detailFragment));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
