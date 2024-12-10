package com.android.smart_grocery_and_meal_planner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.android.smart_grocery_and_meal_planner.Data.ConnectionUtils;
import com.android.smart_grocery_and_meal_planner.Fragment.UserAuthentication_SplashScreen;
import com.android.smart_grocery_and_meal_planner.Fragment.UserFirstVisit_SplashScreen;
import com.android.smart_grocery_and_meal_planner.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_Screen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = firebaseAuth.getCurrentUser();


        if (!ConnectionUtils.isNetworkAvailable(this)) {
            ConnectionUtils.showConnectionErrorDialog(this);
        } else {
            //if connection is available
            if (firebaseUser == null){
                if (savedInstanceState == null) {
                    loadFragment(new UserFirstVisit_SplashScreen());
                }
            }else {
                loadFragment(new UserAuthentication_SplashScreen());
            }
        }

    }

    // Method to load a fragment dynamically
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}