package com.example.fragmentexample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If this is the first time the activity is created, add the fragments
        if (savedInstanceState == null) {
            Fragment1 fragment1 = new Fragment1();
            Fragment2 fragment2 = new Fragment2();

            // Begin transaction to add both fragments
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container1, fragment1);
            transaction.replace(R.id.fragment_container2, fragment2);
            transaction.commit();
        }
    }

    public void sendDataToFragment2(String data) {
        // Find the Fragment2 and update it with the new data
        Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.fragment_container2);
        if (fragment2 instanceof Fragment2) {
            ((Fragment2) fragment2).updateData(data);
        }
    }
}
