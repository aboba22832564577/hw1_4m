package com.geektech.hw1_4m;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.geektech.hw1_4m.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        board();
        BottomNav();
    }

    private void board() {
       if (!App.prefs.isShown()){
          openBoard();
          App.prefs.isShowed();
      }
    }




    private void BottomNav() {
        controller.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.boardFragment || navDestination.getId() ==
                    R.id.detailFragment) {
                binding.navView.setVisibility(View.GONE);
                getSupportActionBar().hide();
            } else {
                binding.navView.setVisibility(View.VISIBLE);
                getSupportActionBar().show();
            }
        });
    }

    private void openBoard() {
        controller.navigate(R.id.boardFragment);
    }

    private void init() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard).build();
        controller = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, controller, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, controller);
    }

}