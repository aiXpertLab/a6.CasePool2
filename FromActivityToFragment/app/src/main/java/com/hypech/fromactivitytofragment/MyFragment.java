package com.hypech.fromactivitytofragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    TextView textView;

    public MyFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        textView = view.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        String message = bundle.getString("message");
        textView.setText(message);
        return view;
    }
}