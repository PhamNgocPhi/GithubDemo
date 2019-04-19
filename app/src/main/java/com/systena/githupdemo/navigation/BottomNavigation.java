package com.systena.githupdemo.navigation;

import com.systena.githupdemo.ui.base.BaseFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BottomNavigation<T extends BaseFragment> {

    private FragmentManager fragmentManager;
    private T currentFragment;
    private int container;


    public BottomNavigation(FragmentManager fragmentManager, int container) {
        this.fragmentManager = fragmentManager;
        this.container = container;
    }

    public void openFragment(Class<T> fragment) {
        if (currentFragment != null && currentFragment.getClass().getName().equalsIgnoreCase(fragment.getName())) {
            return;
        }

        try {
            currentFragment = fragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(container, currentFragment, fragment.getName());
            fragmentTransaction.commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
