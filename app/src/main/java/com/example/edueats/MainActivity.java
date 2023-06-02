package com.example.edueats;

import android.os.Bundle;

import com.example.edueats.interfaces.IHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.edueats.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IHomeFragment {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_favorite_products,
                R.id.navigation_orders)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }


    @Override
    public void goToBasket(){
        navigateToFragment(R.id.navigation_dashboard);
    }

    private void navigateToFragment(int fragmentId) {
        binding.navView.setSelectedItemId(fragmentId);
        navController.navigate(fragmentId);
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}