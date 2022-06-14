package com.example.fragmentremovepopbackstack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addA(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentTransaction.add(R.id.frameLayout, fragmentA, "fragA");
        fragmentTransaction.addToBackStack("aaa");
        fragmentTransaction.commit();
    }

    public void addB(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        fragmentTransaction.add(R.id.frameLayout, fragmentB, "fragB");
        fragmentTransaction.addToBackStack("bbb");
        fragmentTransaction.commit();
    }

    public void addC(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentC fragmentC = new FragmentC();
        fragmentTransaction.add(R.id.frameLayout, fragmentC, "fragC");
        fragmentTransaction.addToBackStack("ccc");
        fragmentTransaction.commit();
    }

    public void removeA(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = (FragmentA) getSupportFragmentManager().findFragmentByTag("fragA");
        if (fragmentA != null) {
            fragmentTransaction.remove(fragmentA);
            fragmentTransaction.commit();
        }
    }

    public void removeB(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag("fragB");
        if (fragmentB != null) {
            fragmentTransaction.remove(fragmentB);
            fragmentTransaction.commit();
        }
    }

    public void removeC(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentC fragmentC = (FragmentC) getSupportFragmentManager().findFragmentByTag("fragC");
        if (fragmentC != null) {
            fragmentTransaction.remove(fragmentC);
            fragmentTransaction.commit();
        }
    }

    public void back(View view) {
        getSupportFragmentManager().popBackStack();
    }

    public void popA(View view) {
        getSupportFragmentManager().popBackStack("aaa", 0);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}