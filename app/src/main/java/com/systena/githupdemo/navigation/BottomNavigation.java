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
            try {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(currentFragment);
                currentFragment = fragment.newInstance();
                fragmentTransaction.replace(container, currentFragment, fragment.getName());
                fragmentTransaction.commit();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment);
                }
                if (fragmentManager.findFragmentByTag(fragment.getName()) != null) {
                    currentFragment = (T) fragmentManager.findFragmentByTag(fragment.getName());
                    fragmentTransaction.show(currentFragment);
                } else {
                    currentFragment = fragment.newInstance();
                    fragmentTransaction.add(container, currentFragment, fragment.getName());
                }
                fragmentTransaction.commit();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
