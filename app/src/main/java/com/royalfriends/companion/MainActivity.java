package com.royalfriends.companion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.royalfriends.companion.Mainfrgament.Goalfragment;
import com.royalfriends.companion.Mainfrgament.Home;
import com.royalfriends.companion.Mainfrgament.Problem;
import com.royalfriends.companion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.goal));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_home_24));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.communication));

        binding.bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new Goalfragment();
                        break;
                    case 2:
                        fragment = new Home();
                        break;
                    case 3:
                        fragment = new Problem();
                        break;

                }

                loadFragment(fragment);
            }
        });
        binding.bottomNavigation.show(2, true);


        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment)
                .commit();
    }
}