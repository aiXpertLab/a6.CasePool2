package com.reapex.sv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.reapex.sv.asr.AsrAudioActivity;

public class Frag1 extends Fragment implements View.OnClickListener {
    final String TAG = this.getClass().getSimpleName();

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.a_frag1, container, false);

        RelativeLayout rGeneral = view.findViewById(R.id.relative_general);
        RelativeLayout rInHouse = view.findViewById(R.id.relative_in_house);
        RelativeLayout rOutside = view.findViewById(R.id.relative_outside);
        RelativeLayout rNoise   = view.findViewById(R.id.relative_noise);

        rOutside.setOnClickListener(this);
        rNoise.setOnClickListener(this);
        rInHouse.setOnClickListener(this);
        rGeneral.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MySP.getInstance().init(getActivity());
        Log.e(TAG, "启动.");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), AsrAudioActivity.class);
        startActivity(intent);
    }
}