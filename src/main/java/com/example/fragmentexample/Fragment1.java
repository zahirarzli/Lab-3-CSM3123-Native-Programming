package com.example.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set a click listener to send data to Fragment2
        view.findViewById(R.id.button_send_data).setOnClickListener(v -> {
            // Send data to Fragment2 via MainActivity
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).sendDataToFragment2("Hello from Fragment1");
            }
        });
    }
}
