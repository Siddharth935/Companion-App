package com.royalfriends.companion.Mainfrgament;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.royalfriends.companion.Adaptors.ViewPagerAdpaters;
import com.royalfriends.companion.Goal.BetterSleep;
import com.royalfriends.companion.Goal.BreakBadHabbit;
import com.royalfriends.companion.Goal.Fit;
import com.royalfriends.companion.Goal.HappyCouple;
import com.royalfriends.companion.Goal.LiveHealthier;
import com.royalfriends.companion.Goal.ReduceStress;
import com.royalfriends.companion.R;

public class Goalfragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public Goalfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goalfragment, container, false);
        addFragment(view);
        return view;
    }
    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.goalTab);
        viewPager = view.findViewById(R.id.NewsLanguageViewPager);

        ViewPagerAdpaters adpaters = new ViewPagerAdpaters(getChildFragmentManager());
        adpaters.addFragment(new BetterSleep(),"Better Sleep");
        adpaters.addFragment(new Fit(),"Fit");
        adpaters.addFragment(new LiveHealthier(),"Live Healthier");
        adpaters.addFragment(new HappyCouple(),"Happy Couple");
        adpaters.addFragment(new ReduceStress(),"Reduce Stress");
        adpaters.addFragment(new BreakBadHabbit(),"Break Bad Habits");
        viewPager.setAdapter(adpaters);
        tabLayout.setupWithViewPager(viewPager);
    }

}