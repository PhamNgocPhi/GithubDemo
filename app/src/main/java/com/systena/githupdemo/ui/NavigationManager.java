package com.systena.githupdemo.ui;

import com.systena.githupdemo.ui.base.BaseFragment;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationManager<T extends BaseFragment> {

    private FragmentManager fragmentManager;
    private String currentFragment;
    private int container;

    public NavigationManager(FragmentManager fragmentManager, @IdRes int container) {
        this.fragmentManager = fragmentManager;
        this.container = container;
    }

    private void openFragment(Class<T> fragment, boolean addToBackStack, boolean isOpenAsRoot) {
        if (currentFragment != null && currentFragment.equalsIgnoreCase(fragment.getName())) {
            return;
        }
        currentFragment = fragment.getName();
        try {
            T instance = fragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (isOpenAsRoot) {
                fragmentTransaction.replace(container, instance, "ROOT");
            } else {
                fragmentTransaction.replace(container, instance);
            }
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getName());
            }
            fragmentTransaction.commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pops all the queued fragments
     */
    private void popEveryFragment() {
        fragmentManager.popBackStackImmediate("ROOT", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * display the next Fragment and add to back stack
     */
    public void open(Class<T> fragment) {
        openFragment(fragment, true, false);
    }

    /**
     * display the next Fragment and no add to back stack
     */
    public void openNoAddToBackStack(Class<T> fragment) {
        openFragment(fragment, false, false);
    }

    /**
     * clear back stack and open fragment as root
     */
    public void openAsRoot(Class<T> fragment) {
        popEveryFragment();
        openFragment(fragment, false, true);
    }

    /**
     * back to previous fragment
     */
    public boolean navigateBack() {
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            return false;
        } else {
            fragmentManager.popBackStackImmediate();
            int currentSize = fragmentManager.getBackStackEntryCount();
            currentFragment = fragmentManager.getBackStackEntryAt(currentSize - 1).getName();
            return true;
        }
    }

    /**
     * back to specify fragment
     */
    public boolean navigateBackTo(Class<T> fragment) {
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            return false;
        } else {
            String name = fragment.getName();
            if (fragmentManager.findFragmentByTag(name) != null) {
                fragmentManager.popBackStackImmediate(name, 0);
                int currentSize = fragmentManager.getBackStackEntryCount();
                currentFragment = fragmentManager.getBackStackEntryAt(currentSize - 1).getName();
                return true;
            } else {
                return false;
            }
        }
    }


}
