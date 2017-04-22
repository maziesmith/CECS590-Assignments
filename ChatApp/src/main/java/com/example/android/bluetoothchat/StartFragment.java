package com.example.android.bluetoothchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Sumeet on 4/17/2017.
 */

public class StartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        Button bluetoothButton = (Button)view.findViewById(R.id.start_bluetooth);
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                BluetoothChatFragment fragment = new BluetoothChatFragment();
                transaction.replace(R.id.main_layout, fragment);
                transaction.commit();
            }
        });

        return view;
    }
}
